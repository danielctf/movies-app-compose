package com.example.movieapp.ui.view.menu

import com.example.movieapp.R
import com.example.movieapp.domain.entity.MovieType
import com.example.movieapp.ui.entity.MenuItem

fun newMenuItemsList() = listOf(
    MenuItem(
        R.string.top_rated_movies,
        MovieType.TOP_RATED
    )
)
