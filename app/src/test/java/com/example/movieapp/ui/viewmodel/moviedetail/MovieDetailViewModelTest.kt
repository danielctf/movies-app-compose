package com.example.movieapp.ui.viewmodel.moviedetail

import androidx.lifecycle.SavedStateHandle
import com.example.movieapp.domain.usecase.GetMovieUseCase
import com.example.movieapp.ui.entity.main.Navigation
import com.example.movieapp.ui.entity.moviedetail.MovieDetailState
import com.example.movieapp.utils.MainCoroutineRule
import com.example.movieapp.utils.MockkTest
import com.example.movieapp.utils.newMovie
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieDetailViewModelTest : MockkTest() {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var savedStateHandle: SavedStateHandle
    @MockK
    private lateinit var getMovieUseCase: GetMovieUseCase
    private lateinit var viewModel: MovieDetailViewModel

    private val movie = newMovie()

    @Before
    fun setUp() {
        val uid = "uid"
        every { savedStateHandle.get<String>(Navigation.MovieDetail.MOVIE) } returns uid
        coEvery { getMovieUseCase(uid) } returns movie
        viewModel = MovieDetailViewModel(savedStateHandle, getMovieUseCase)
    }

    @Test
    fun onInit_setContent() {
        // Assert
        assertEquals(MovieDetailState.Content(movie), viewModel.state.value)
    }
}
