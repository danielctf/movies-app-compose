package com.example.movieapp.ui.viewmodel.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.entity.MovieType
import com.example.movieapp.ui.entity.main.Navigation
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val navigationItemsList = listOf(MovieType.TOP_RATED, MovieType.POPULAR)

    private val _goToMovies = MutableSharedFlow<String>()
    val goToMovies = _goToMovies.asSharedFlow()

    private val _selectedNavigationItem = mutableStateOf(navigationItemsList.first())
    val selectedNavigationItem: State<MovieType> = _selectedNavigationItem

    fun onNavigationItemClicked(movieType: MovieType) {
        _selectedNavigationItem.value = movieType
        viewModelScope.launch {
            _goToMovies.emit(
                when (movieType) {
                    MovieType.TOP_RATED -> Navigation.TopRatedMovies.route
                    MovieType.POPULAR -> Navigation.PopularMovies.route
                    MovieType.NONE -> throw IllegalStateException("Invalid destination $movieType")
                }
            )
        }
    }
}
