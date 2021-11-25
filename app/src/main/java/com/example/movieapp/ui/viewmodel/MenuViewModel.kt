package com.example.movieapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.R
import com.example.movieapp.domain.entity.MovieType
import com.example.movieapp.ui.entity.MenuItem
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MenuViewModel : ViewModel() {

    val menuItemsList = listOf(
        MenuItem(
            R.string.top_rated_movies,
            MovieType.TOP_RATED
        ),
        MenuItem(
            R.string.popular_movies,
            MovieType.POPULAR
        )
    )

    private val _goToMovies = MutableSharedFlow<MovieType>()
    val goToMovies = _goToMovies.asSharedFlow()

    fun onMovieTypeClicked(type: MovieType) {
        viewModelScope.launch {
            _goToMovies.emit(type)
        }
    }
}
