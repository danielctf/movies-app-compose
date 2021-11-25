package com.example.movieapp.domain.usecase

import com.example.movieapp.domain.entity.MovieType
import com.example.movieapp.domain.entity.Result
import com.example.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RefreshMoviesUseCase @Inject constructor(
    private val repository: MovieRepository,
    private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(type: MovieType): Result<Unit> = withContext(dispatcher) {
        repository.refreshMovies(type)
    }
}
