package com.example.movieapp.domain.usecase

import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.utils.MockkTest
import com.example.movieapp.utils.newMovie
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetMovieUseCaseTest : MockkTest() {

    @RelaxedMockK
    private lateinit var repository: MovieRepository
    private lateinit var useCase: GetMovieUseCase

    @Before
    fun setUp() {
        useCase = GetMovieUseCase(repository, TestCoroutineDispatcher())
    }

    @Test
    fun onInvoke_returnMovie() = runBlockingTest {
        // Arrange
        val uid = "uid"
        val movie = newMovie()
        coEvery { repository.getMovie(uid) } returns movie

        // Act
        val result = useCase(uid)

        // Assert
        assertEquals(movie, result)
    }
}
