package com.example.movieapp.ui.viewmodel.movies

import com.example.movieapp.domain.entity.MovieType
import com.example.movieapp.utils.MockkTest
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class PopularMoviesViewModelTest : MockkTest() {

    @RelaxedMockK
    private lateinit var handler: MoviesViewModelHandler
    private lateinit var viewModel: PopularMoviesViewModel

    @Before
    fun setUp() {
        viewModel = PopularMoviesViewModel(handler)
    }

    @Test
    fun onInit_setType() {
        // Assert
        verify { handler.setType(MovieType.POPULAR) }
    }
}
