package com.example.movieapp.domain.usecase

import com.example.movieapp.domain.di.IoDispatcher
import com.example.movieapp.domain.entity.Movie
import com.example.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val repository: MovieRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(uid: String): Movie = withContext(dispatcher) {
        repository.getMovie(uid)
    }
}
