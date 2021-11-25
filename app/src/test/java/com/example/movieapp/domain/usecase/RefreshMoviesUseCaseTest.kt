package com.example.movieapp.domain.usecase

import com.example.movieapp.domain.entity.MovieType
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.utils.MockkTest
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RefreshMoviesUseCaseTest : MockkTest() {

    @RelaxedMockK
    private lateinit var repository: MovieRepository
    private lateinit var useCase: RefreshMoviesUseCase

    @Before
    fun setUp() {
        useCase = RefreshMoviesUseCase(repository, TestCoroutineDispatcher())
    }

    @Test
    fun onInvoke_callRepository() = runBlockingTest {
        // Arrange
        val type = MovieType.TOP_RATED

        // Act
        useCase(type)

        // Assert
        coVerify { repository.refreshMovies(type) }
    }
}
