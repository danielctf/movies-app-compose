package com.example.movieapp.ui.view.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieapp.ui.entity.main.Navigation
import com.example.movieapp.ui.view.moviedetail.MovieDetail
import com.example.movieapp.ui.view.movies.PopularMoviesView
import com.example.movieapp.ui.view.movies.TopRatedMoviesView

@Composable
fun MainNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Navigation.TopRatedMovies.route) {
        addTopRatedMovies(navController)
        addPopularMovies(navController)
        addMovieDetail()
    }
}

private fun NavGraphBuilder.addTopRatedMovies(navController: NavController) {
    composable(Navigation.TopRatedMovies.route) {
        TopRatedMoviesView(
            goToMovieDetail = { navController.navigate("${Navigation.MovieDetail.route}/$it") }
        )
    }
}

private fun NavGraphBuilder.addPopularMovies(navController: NavController) {
    composable(Navigation.PopularMovies.route) {
        PopularMoviesView(
            goToMovieDetail = { navController.navigate("${Navigation.MovieDetail.route}/$it") }
        )
    }
}

private fun NavGraphBuilder.addMovieDetail() {
    composable("${Navigation.MovieDetail.route}/{${Navigation.MovieDetail.MOVIE}}") {
        MovieDetail()
    }
}
