package com.example.movieapp.ui.view.movies

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.ui.viewmodel.movies.TopRatedMoviesViewModel

@Composable
fun TopRatedMoviesView(goToMovieDetail: (String) -> Unit) {
    Movies(
        viewModel = hiltViewModel<TopRatedMoviesViewModel>(),
        goToMovieDetail = goToMovieDetail
    )
}
