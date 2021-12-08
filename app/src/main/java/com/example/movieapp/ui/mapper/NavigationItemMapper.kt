package com.example.movieapp.ui.mapper

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import com.example.movieapp.R
import com.example.movieapp.domain.entity.MovieType
import com.example.movieapp.ui.entity.main.NavigationItem

fun List<MovieType>.mapToUi(): List<NavigationItem> = mapNotNull { movieType ->
    when (movieType) {
        MovieType.TOP_RATED -> NavigationItem(
            textId = R.string.top_rated_movies,
            icon = Icons.Default.Star,
            type = movieType
        )
        MovieType.POPULAR -> NavigationItem(
            textId = R.string.popular_movies,
            icon = Icons.Default.Person,
            type = movieType
        )
        MovieType.NONE -> null
    }
}
