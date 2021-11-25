package com.example.movieapp.utils

import com.example.movieapp.data.entity.DataMovie
import com.example.movieapp.data.entity.DataMovieResponse
import com.example.movieapp.data.mapper.toDomain
import com.example.movieapp.domain.entity.MovieType

fun newDataMoviesList(type: MovieType) = listOf(
    DataMovie(
        overview = "overview 1",
        title = "title 1",
        releaseDate = "releaseDate 1",
        averageVote = 8.3,
        posterPath = "posterPath 1",
        type = type
    ),
    DataMovie(
        overview = "overview 2",
        title = "title 2",
        releaseDate = "releaseDate 2",
        averageVote = 7.5,
        posterPath = "posterPath 2",
        type = type
    )
)

fun newDataMovieResponse(type: MovieType) = DataMovieResponse(newDataMoviesList(type))

fun newMoviesList() = newDataMoviesList(MovieType.NONE).toDomain()
