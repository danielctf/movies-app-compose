package com.example.movieapp.ui.viewmodel

import com.example.movieapp.R
import com.example.movieapp.domain.entity.MovieType
import com.example.movieapp.ui.entity.MenuItem
import com.example.movieapp.utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toCollection
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MenuViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private val viewModel = MenuViewModel()

    @Test
    fun onGetMenuItemsList_returnTopRatedAndPopularTypes() {
        // Arrange
        val expected = listOf(
            MenuItem(
                R.string.top_rated_movies,
                MovieType.TOP_RATED
            ),
            MenuItem(
                R.string.popular_movies,
                MovieType.POPULAR
            )
        )

        // Act
        val result = viewModel.menuItemsList

        // Assert
        assertEquals(expected, result)
    }

    @Test
    fun onTopRatedMovieTypeClicked_goToTopRatedMovies() = runBlockingTest {
        // Arrange
        val type = MovieType.TOP_RATED
        val effectsList = mutableListOf<MovieType>()

        // Act
        val job = launch { viewModel.goToMovies.toCollection(effectsList) }
        viewModel.onMovieTypeClicked(type)
        job.cancel()

        // Assert
        assertEquals(listOf(type), effectsList)
    }

    @Test
    fun onPopularMovieTypeClicked_goToPopularMovies() = runBlockingTest {
        // Arrange
        val type = MovieType.POPULAR
        val effectsList = mutableListOf<MovieType>()

        // Act
        val job = launch { viewModel.goToMovies.toCollection(effectsList) }
        viewModel.onMovieTypeClicked(type)
        job.cancel()

        // Assert
        assertEquals(listOf(type), effectsList)
    }
}
