package com.example.movieapp.ui.viewmodel

import com.example.movieapp.domain.entity.Movie
import com.example.movieapp.domain.entity.MovieType
import com.example.movieapp.domain.entity.Result
import com.example.movieapp.domain.usecase.GetMoviesUseCase
import com.example.movieapp.domain.usecase.RefreshMoviesUseCase
import com.example.movieapp.utils.MainCoroutineRule
import com.example.movieapp.utils.MockkTest
import com.example.movieapp.utils.newMoviesList
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toCollection
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesViewModelTest : MockkTest() {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var getMoviesUseCase: GetMoviesUseCase
    @MockK
    private lateinit var refreshMoviesUseCase: RefreshMoviesUseCase
    private lateinit var viewModel: MoviesViewModel

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(getMoviesUseCase, refreshMoviesUseCase)
    }

    @Test
    fun onViewCreated_loadMovies() {
        // Arrange
        val type = MovieType.TOP_RATED
        val moviesList = newMoviesList()
        every { getMoviesUseCase(type) } returns flowOf(moviesList)

        // Act
        viewModel.onViewCreated(type)

        // Assert
        assertEquals(moviesList, viewModel.filteredMoviesList.value)
    }

    @Test
    fun onViewCreated_refreshMovies() {
        // Arrange
        val type = MovieType.TOP_RATED
        coEvery { refreshMoviesUseCase(type) } returns Result.Success(Unit)

        // Act
        viewModel.onViewCreated(type)

        // Assert
        coVerify { refreshMoviesUseCase(type) }
    }

    @Test
    fun onRefreshMoviesError_showError() {
        // Arrange
        val type = MovieType.TOP_RATED
        coEvery { refreshMoviesUseCase(type) } returns Result.Error(Exception())

        // Act
        viewModel.onViewCreated(type)

        // Assert
        assertEquals(true, viewModel.showError.value)
    }

    @Test
    fun onErrorConfirmed_hideError() {
        // Arrange
        val type = MovieType.TOP_RATED
        coEvery { refreshMoviesUseCase(type) } returns Result.Error(Exception())
        viewModel.onViewCreated(type)

        // Act
        viewModel.onErrorConfirmed()

        // Assert
        assertEquals(false, viewModel.showError.value)
    }

    @Test
    fun onSearchTextChanged_filterMovies() {
        // Arrange
        val type = MovieType.TOP_RATED
        val moviesList = newMoviesList()
        every { getMoviesUseCase(type) } returns flowOf(moviesList)
        coEvery { refreshMoviesUseCase(type) } returns Result.Error(Exception())
        viewModel.onViewCreated(type)

        // Act
        viewModel.onSearchTextChanged(moviesList.first().title.uppercase())

        // Assert
        assertEquals(listOf(moviesList.first()), viewModel.filteredMoviesList.value)
    }

    @Test
    fun onMovieClicked_goToMovieDetail() = runBlockingTest {
        // Arrange
        val movie = newMoviesList().first()
        val eventsList = mutableListOf<Movie>()

        // Act
        val job = launch { viewModel.goToMovieDetail.toCollection(eventsList) }
        viewModel.onMovieClicked(movie)
        job.cancel()

        // Assert
        assertEquals(listOf(movie), eventsList)
    }
}
