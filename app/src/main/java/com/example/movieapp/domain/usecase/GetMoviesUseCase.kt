package com.example.movieapp.domain.usecase

import com.example.movieapp.domain.entity.Movie
import com.example.movieapp.domain.entity.MovieType
import com.example.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    operator fun invoke(type: MovieType): Flow<List<Movie>> = repository.getMoviesList(type)
}
