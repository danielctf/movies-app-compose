package com.example.movieapp.ui.viewmodel.movies

import com.example.movieapp.domain.entity.MovieType
import com.example.movieapp.utils.MockkTest
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class TopRatedMoviesViewModelTest : MockkTest() {

    @RelaxedMockK
    private lateinit var handler: MoviesViewModelHandler
    private lateinit var viewModel: TopRatedMoviesViewModel

    @Before
    fun setUp() {
        viewModel = TopRatedMoviesViewModel(handler)
    }

    @Test
    fun onInit_setType() {
        // Assert
        verify { handler.setType(MovieType.TOP_RATED) }
    }
}
