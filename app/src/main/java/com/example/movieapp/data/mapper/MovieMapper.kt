package com.example.movieapp.data.mapper

import com.example.movieapp.data.entity.DataMovie
import com.example.movieapp.domain.entity.Movie

fun List<DataMovie>.toDomain(): List<Movie> = map {
    with(it) {
        Movie(
            overview = overview,
            title = title,
            releaseDate = releaseDate,
            averageVote = averageVote,
            posterPath = posterPath
        )
    }
}
