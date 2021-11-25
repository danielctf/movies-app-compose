package com.example.movieapp.data.repository

import com.example.movieapp.data.datasource.MovieRemoteDataSource
import com.example.movieapp.data.datasource.dao.MovieDao
import com.example.movieapp.data.mapper.toDomain
import com.example.movieapp.domain.entity.MovieType
import com.example.movieapp.domain.entity.Result
import com.example.movieapp.utils.MockkTest
import com.example.movieapp.utils.newDataMoviesList
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieRepositoryImplTest : MockkTest() {

    @MockK
    private lateinit var remote: MovieRemoteDataSource
    @RelaxedMockK
    private lateinit var local: MovieDao
    private lateinit var repository: MovieRepositoryImpl

    @Before
    fun setUp() {
        repository = MovieRepositoryImpl(remote, local)
    }

    @Test
    fun onGetTopRatedMoviesList_returnMoviesList() = runBlockingTest {
        // Arrange
        val type = MovieType.TOP_RATED
        val moviesList = newDataMoviesList(type)
        every { local.getMoviesList(type) } returns flowOf(moviesList)

        // Act
        val result = repository.getMoviesList(type)

        // Assert
        assertEquals(moviesList.toDomain(), result.toList().firstOrNull())
    }

    @Test
    fun onRefreshTopRatedMoviesSuccess_returnSuccess() = runBlockingTest {
        // Arrange
        val type = MovieType.TOP_RATED
        val moviesList = newDataMoviesList(type)
        coEvery { remote.getTopRatedMoviesList() } returns Result.Success(moviesList)

        // Act
        val result = repository.refreshMovies(type)

        // Assert
        verifyOrder {
            local.delete(type)
            local.insert(moviesList)
        }
        assertEquals(Result.Success(Unit), result)
    }

    @Test
    fun onRefreshTopRatedMoviesError_returnError() = runBlockingTest {
        // Arrange
        val error = Result.Error(Exception("Some exception"))
        coEvery { remote.getTopRatedMoviesList() } returns error

        // Act
        val result = repository.refreshMovies(MovieType.TOP_RATED)

        // Assert
        verify { local wasNot Called }
        assertEquals(error, result)
    }

    @Test
    fun onRefreshPopularMoviesSuccess_returnSuccess() = runBlockingTest {
        // Arrange
        val type = MovieType.POPULAR
        val moviesList = newDataMoviesList(type)
        coEvery { remote.getPopularMoviesList() } returns Result.Success(moviesList)

        // Act
        val result = repository.refreshMovies(type)

        // Assert
        verifyOrder {
            local.delete(type)
            local.insert(moviesList)
        }
        assertEquals(Result.Success(Unit), result)
    }

    @Test
    fun onRefreshNoneMovies_returnError() = runBlockingTest {
        // Act
        val result = repository.refreshMovies(MovieType.NONE)

        // Assert
        verify {
            local wasNot Called
            remote wasNot Called
        }
        assertTrue(result is Result.Error)
    }
}
