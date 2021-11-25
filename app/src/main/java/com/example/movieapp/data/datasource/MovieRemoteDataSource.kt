package com.example.movieapp.data.datasource

import com.example.movieapp.data.datasource.service.MovieService
import com.example.movieapp.data.entity.DataMovie
import com.example.movieapp.domain.entity.MovieType
import com.example.movieapp.domain.entity.Result
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val movieService: MovieService
) : BaseRemoteDataSource() {

    suspend fun getTopRatedMoviesList(): Result<List<DataMovie>> = getResult {
        movieService.getTopRatedMoviesList().moviesList.onEach {
            it.type = MovieType.TOP_RATED
        }
    }

    suspend fun getPopularMoviesList(): Result<List<DataMovie>> = getResult {
        movieService.getPopularMoviesList().moviesList.onEach {
            it.type = MovieType.POPULAR
        }
    }
}
