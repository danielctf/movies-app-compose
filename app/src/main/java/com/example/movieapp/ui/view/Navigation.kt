package com.example.movieapp.ui.view

sealed class Navigation(val route: String) {
    object Menu : Navigation("menu")
    object Movies : Navigation("movies")
    object MovieDetail : Navigation("movie-detail?movie=")
}
