package com.example.movieapp.ui.view.movies

import com.example.movieapp.domain.entity.Movie

fun newMovie() = Movie(
    uid = "uid",
    overview = "Super duper movie",
    title = "The Godfather",
    releaseDate = "1972-03-14",
    averageVote = 8.7,
    posterPath = null
)
