package com.example.movieapp.ui.viewmodel.movies

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
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesViewModelHandlerImplTest : MockkTest() {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var getMoviesUseCase: GetMoviesUseCase
    @MockK
    private lateinit var refreshMoviesUseCase: RefreshMoviesUseCase
    private lateinit var viewModel: MoviesViewModelHandlerImpl

    private val type = MovieType.TOP_RATED
    private val moviesList = newMoviesList()

    @Before
    fun setUp() {
        viewModel = MoviesViewModelHandlerImpl(
            getMoviesUseCase,
            refreshMoviesUseCase,
            TestCoroutineDispatcher()
        )
        every { getMoviesUseCase(type) } returns flowOf(moviesList)
        coEvery { refreshMoviesUseCase(type) } returns Result.Success(Unit)
    }

    @Test
    fun onSetType_loadMovies() {
        // Act
        viewModel.setType(type)

        // Assert
        assertEquals(moviesList, viewModel.filteredMoviesList.value)
    }

    @Test
    fun onSetType_refreshMovies() {
        // Act
        viewModel.setType(type)

        // Assert
        coVerify { refreshMoviesUseCase(type) }
    }

    @Test
    fun onRefreshMoviesError_showError() {
        // Arrange
        coEvery { refreshMoviesUseCase(type) } returns Result.Error(Exception())

        // Act
        viewModel.setType(type)

        // Assert
        assertEquals(true, viewModel.showError.value)
    }

    @Test
    fun onErrorConfirmed_hideError() {
        // Arrange
        coEvery { refreshMoviesUseCase(type) } returns Result.Error(Exception())
        viewModel.setType(type)

        // Act
        viewModel.onErrorConfirmed()

        // Assert
        assertEquals(false, viewModel.showError.value)
    }

    @Test
    fun onSearchTextChanged_filterMovies() {
        // Arrange
        viewModel.setType(type)

        // Act
        viewModel.onSearchTextChanged(moviesList.first().title.uppercase())

        // Assert
        assertEquals(listOf(moviesList.first()), viewModel.filteredMoviesList.value)
    }

    @Test
    fun onMovieClicked_goToMovieDetail() = runBlockingTest {
        // Arrange
        val effectsList = mutableListOf<String>()
        val uid = "uid"

        // Act
        val job = launch { viewModel.goToMovieDetail.toCollection(effectsList) }
        viewModel.onMovieClicked(uid)
        job.cancel()

        // Assert
        assertEquals(listOf(uid), effectsList)
    }
}
