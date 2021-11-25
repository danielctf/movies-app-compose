package com.example.movieapp.data.datasource

import com.example.movieapp.data.datasource.service.MovieService
import com.example.movieapp.domain.entity.MovieType
import com.example.movieapp.utils.MockkTest
import com.example.movieapp.utils.getData
import com.example.movieapp.utils.getError
import com.example.movieapp.utils.newDataMovieResponse
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieRemoteDataSourceTest : MockkTest() {

    @MockK
    private lateinit var service: MovieService
    private lateinit var dataSource: MovieRemoteDataSource

    @Before
    fun setUp() {
        dataSource = MovieRemoteDataSource(service)
    }

    @Test
    fun onGetTopRatedMoviesListSuccess_setTypeToTopRated() = runBlockingTest {
        // Arrange
        val response = newDataMovieResponse(MovieType.NONE)
        val expected = response.moviesList.map { it.copy(type = MovieType.TOP_RATED) }
        coEvery { service.getTopRatedMoviesList() } returns response

        // Act
        val result = dataSource.getTopRatedMoviesList()

        // Assert
        assertEquals(expected, result.getData())
    }

    @Test
    fun onGetTopRatedMoviesListError_returnError() = runBlockingTest {
        // Arrange
        val exception = Exception("Some exception")
        coEvery { service.getTopRatedMoviesList() } throws exception

        // Act
        val result = dataSource.getTopRatedMoviesList()

        // Assert
        assertEquals(exception, result.getError())
    }

    @Test
    fun onGetPopularMoviesListSuccess_setTypeToPopular() = runBlockingTest {
        // Arrange
        val response = newDataMovieResponse(MovieType.NONE)
        val expected = response.moviesList.map { it.copy(type = MovieType.POPULAR) }
        coEvery { service.getPopularMoviesList() } returns response

        // Act
        val result = dataSource.getPopularMoviesList()

        // Assert
        assertEquals(expected, result.getData())
    }
}
