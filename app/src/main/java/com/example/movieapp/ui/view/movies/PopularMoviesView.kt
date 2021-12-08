package com.example.movieapp.ui.view.movies

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.ui.viewmodel.movies.PopularMoviesViewModel

@Composable
fun PopularMoviesView(goToMovieDetail: (String) -> Unit) {
    Movies(
        viewModel = hiltViewModel<PopularMoviesViewModel>(),
        goToMovieDetail = goToMovieDetail
    )
}
