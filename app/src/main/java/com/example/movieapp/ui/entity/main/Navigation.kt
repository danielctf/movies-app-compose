package com.example.movieapp.ui.entity.main

sealed class Navigation(val route: String) {
    object TopRatedMovies : Navigation("top-rated-movies")
    object PopularMovies : Navigation("popular-movies")
    object MovieDetail : Navigation("movie-detail") {
        const val MOVIE = "movie"
    }
}
