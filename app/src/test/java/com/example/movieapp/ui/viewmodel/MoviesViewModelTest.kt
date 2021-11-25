package com.example.movieapp.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.example.movieapp.domain.entity.Movie
import com.example.movieapp.domain.entity.MovieType
import com.example.movieapp.domain.entity.Result
import com.example.movieapp.domain.usecase.GetMoviesUseCase
import com.example.movieapp.domain.usecase.RefreshMoviesUseCase
import com.example.movieapp.ui.view.Navigation
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
    private lateinit var savedStateHandle: SavedStateHandle
    @MockK
    private lateinit var getMoviesUseCase: GetMoviesUseCase
    @MockK
    private lateinit var refreshMoviesUseCase: RefreshMoviesUseCase

    private val type = MovieType.TOP_RATED
    private val moviesList = newMoviesList()

    @Before
    fun setUp() {
        every { getMoviesUseCase(type) } returns flowOf(moviesList)
        coEvery { refreshMoviesUseCase(type) } returns Result.Success(Unit)
        every {
            savedStateHandle.get<String>(Navigation.Movies.TYPE)
        } returns MovieType.TOP_RATED.toString()
    }

    @Test
    fun onInit_loadMovies() {
        // Act
        val viewModel = newViewModel()

        // Assert
        assertEquals(moviesList, viewModel.filteredMoviesList.value)
    }

    private fun newViewModel() = MoviesViewModel(
        savedStateHandle,
        getMoviesUseCase,
        refreshMoviesUseCase
    )

    @Test
    fun onInit_refreshMovies() {
        // Act
        newViewModel()

        // Assert
        coVerify { refreshMoviesUseCase(type) }
    }

    @Test
    fun onRefreshMoviesError_showError() {
        // Arrange
        coEvery { refreshMoviesUseCase(type) } returns Result.Error(Exception())

        // Act
        val viewModel = newViewModel()

        // Assert
        assertEquals(true, viewModel.showError.value)
    }

    @Test
    fun onErrorConfirmed_hideError() {
        // Arrange
        coEvery { refreshMoviesUseCase(type) } returns Result.Error(Exception())
        val viewModel = newViewModel()

        // Act
        viewModel.onErrorConfirmed()

        // Assert
        assertEquals(false, viewModel.showError.value)
    }

    @Test
    fun onSearchTextChanged_filterMovies() {
        // Arrange
        val viewModel = newViewModel()

        // Act
        viewModel.onSearchTextChanged(moviesList.first().title.uppercase())

        // Assert
        assertEquals(listOf(moviesList.first()), viewModel.filteredMoviesList.value)
    }

    @Test
    fun onMovieClicked_goToMovieDetail() = runBlockingTest {
        // Arrange
        val viewModel = newViewModel()
        val eventsList = mutableListOf<Movie>()

        // Act
        val job = launch { viewModel.goToMovieDetail.toCollection(eventsList) }
        viewModel.onMovieClicked(moviesList.last())
        job.cancel()

        // Assert
        assertEquals(listOf(moviesList.last()), eventsList)
    }
}
