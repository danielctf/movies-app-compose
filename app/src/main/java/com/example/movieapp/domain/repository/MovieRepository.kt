package com.example.movieapp.domain.repository

import com.example.movieapp.domain.entity.Movie
import com.example.movieapp.domain.entity.MovieType
import com.example.movieapp.domain.entity.Result
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMoviesList(type: MovieType): Flow<List<Movie>>
    suspend fun refreshMovies(type: MovieType): Result<Unit>
    suspend fun getMovie(uid: String): Movie
}
