package com.example.movieapp.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.entity.Movie
import com.example.movieapp.domain.entity.MovieType
import com.example.movieapp.domain.entity.Result
import com.example.movieapp.domain.usecase.GetMoviesUseCase
import com.example.movieapp.domain.usecase.RefreshMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val refreshMoviesUseCase: RefreshMoviesUseCase
) : ViewModel() {

    private var moviesList = emptyList<Movie>()

    private val _filteredMoviesList = mutableStateOf(emptyList<Movie>())
    val filteredMoviesList: State<List<Movie>> = _filteredMoviesList

    private val _searchText = mutableStateOf("")
    val searchText: State<String> = _searchText

    private val _showError = mutableStateOf(false)
    val showError: State<Boolean> = _showError

    private val _goToMovieDetail = MutableSharedFlow<Movie>()
    val goToMovieDetail = _goToMovieDetail.asSharedFlow()

    fun onViewCreated(type: MovieType) {
        loadMovies(type)
        refreshMovies(type)
    }

    private fun loadMovies(type: MovieType) {
        viewModelScope.launch {
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
        viewModelScope.launch {
            refreshMoviesUseCase(type).also {
                if (it is Result.Error) {
                    _showError.value = true
                }
            }
        }
    }

    fun onMovieClicked(movie: Movie) {
        viewModelScope.launch {
            _goToMovieDetail.emit(movie)
        }
    }

    fun onSearchTextChanged(text: String) {
        _searchText.value = text
        filterMoviesList()
    }

    fun onErrorConfirmed() {
        _showError.value = false
    }
}
