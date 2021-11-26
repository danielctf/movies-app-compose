package com.example.movieapp.ui.view

sealed class Navigation(val route: String) {
    object Menu : Navigation("menu")
    object Movies : Navigation("movies") {
        const val TYPE = "type"
    }
    object MovieDetail : Navigation("movie-detail") {
        const val MOVIE = "movie"
    }
}
