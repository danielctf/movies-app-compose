package com.example.movieapp.domain.usecase

import com.example.movieapp.domain.entity.MovieType
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.utils.MockkTest
import com.example.movieapp.utils.newMoviesList
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetMoviesUseCaseTest : MockkTest() {

    @MockK
    private lateinit var repository: MovieRepository
    private lateinit var useCase: GetMoviesUseCase

    @Before
    fun setUp() {
        useCase = GetMoviesUseCase(repository)
    }

    @Test
    fun onInvoke_getMoviesList() = runBlockingTest {
        // Arrange
        val type = MovieType.TOP_RATED
        val moviesList = newMoviesList()
        every { repository.getMoviesList(type) } returns flowOf(moviesList)

        // Act
        val result = useCase(type)

        // Assert
        assertEquals(moviesList, result.toList().firstOrNull())
    }
}
