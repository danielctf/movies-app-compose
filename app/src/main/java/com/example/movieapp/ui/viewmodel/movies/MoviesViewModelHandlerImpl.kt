package com.example.movieapp.ui.viewmodel.movies

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.movieapp.domain.di.MainDispatcher
import com.example.movieapp.domain.entity.Movie
import com.example.movieapp.domain.entity.MovieType
import com.example.movieapp.domain.entity.Result
import com.example.movieapp.domain.usecase.GetMoviesUseCase
import com.example.movieapp.domain.usecase.RefreshMoviesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesViewModelHandlerImpl @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val refreshMoviesUseCase: RefreshMoviesUseCase,
    @MainDispatcher private val dispatcher: CoroutineDispatcher
) : MoviesViewModelHandler,
    CoroutineScope by CoroutineScope(dispatcher) {

    private var moviesList = emptyList<Movie>()

    private val _filteredMoviesList = mutableStateOf(emptyList<Movie>())
    override val filteredMoviesList: State<List<Movie>> = _filteredMoviesList

    private val _searchText = mutableStateOf("")
    override val searchText: State<String> = _searchText

    private val _showError = mutableStateOf(false)
    override val showError: State<Boolean> = _showError

    private val _goToMovieDetail = MutableSharedFlow<String>()
    override val goToMovieDetail = _goToMovieDetail.asSharedFlow()

    override fun setType(type: MovieType) {
        loadMovies(type)
        refreshMovies(type)
    }

    private fun loadMovies(type: MovieType) {
        launch {
            try {
                getMoviesUseCase(type).collect {
                    moviesList = it
                    filterMoviesList()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun filterMoviesList() {
        _filteredMoviesList.value = moviesList.filter {
            it.title.contains(searchText.value, true)
        }
    }

    private fun refreshMovies(type: MovieType) {
        launch {
            refreshMoviesUseCase(type).also {
                if (it is Result.Error) {
                    _showError.value = true
                }
            }
        }
    }

    override fun onMovieClicked(uid: String) {
        launch {
            _goToMovieDetail.emit(uid)
        }
    }

    override fun onSearchTextChanged(text: String) {
        _searchText.value = text
        filterMoviesList()
    }

    override fun onErrorConfirmed() {
        _showError.value = false
    }
}
