package com.example.movieapp.ui.view.movie

import com.example.movieapp.domain.entity.Movie

fun newMovie() = Movie(
    overview = "Super duper movie",
    title = "The Godfather",
    releaseDate = "1972-03-14",
    averageVote = 8.7,
    posterPath = null
)
