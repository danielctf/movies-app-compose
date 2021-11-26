package com.example.movieapp.ui.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.ui.view.menu.Menu
import com.example.movieapp.ui.view.movie.Movies
import com.example.movieapp.ui.view.moviedetail.MovieDetail

@Composable
fun MainNavigator() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Navigation.Menu.route) {
        addMenu(navController)
        addMovies(navController)
        addMovieDetail()
    }
}

private fun NavGraphBuilder.addMenu(navController: NavController) {
    composable(Navigation.Menu.route) {
        Menu(goToMovies = { navController.navigate("${Navigation.Movies.route}/$it") })
    }
}

private fun NavGraphBuilder.addMovies(navController: NavController) {
    val movies = Navigation.Movies
    composable("${movies.route}/{${movies.TYPE}}") {
        Movies(
            goToMovieDetail = {
                navController.navigate("${Navigation.MovieDetail.route}/$it")
            }
        )
    }
}

private fun NavGraphBuilder.addMovieDetail() {
    val movieDetail = Navigation.MovieDetail
    composable("${movieDetail.route}/{${movieDetail.MOVIE}}") {
        MovieDetail()
    }
}
