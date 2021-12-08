package com.example.movieapp.ui.viewmodel.main

import com.example.movieapp.domain.entity.MovieType
import com.example.movieapp.ui.entity.main.Navigation
import com.example.movieapp.utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toCollection
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private val viewModel = MainViewModel()

    @Test
    fun onGetNavigationItemsList_returnNavigationItemsList() {
        // Arrange
        val expected = listOf(MovieType.TOP_RATED, MovieType.POPULAR)

        // Act
        val result = viewModel.navigationItemsList

        // Assert
        assertEquals(expected, result)
    }

    @Test
    fun onNavigationItemClicked_setSelectedNavigationItem() {
        // Arrange
        val type = MovieType.TOP_RATED

        // Act
        viewModel.onNavigationItemClicked(type)

        // Assert
        assertEquals(type, viewModel.selectedNavigationItem.value)
    }

    @Test
    fun onNavigationItemClicked_goToMovies() = runBlockingTest {
        // Arrange
        val effectsList = mutableListOf<String>()

        // Act
        val job = launch { viewModel.goToMovies.toCollection(effectsList) }
        viewModel.onNavigationItemClicked(MovieType.POPULAR)
        viewModel.onNavigationItemClicked(MovieType.TOP_RATED)
        job.cancel()

        // Assert
        assertEquals(
            listOf(
                Navigation.PopularMovies.route,
                Navigation.TopRatedMovies.route
            ),
            effectsList
        )
    }
}
