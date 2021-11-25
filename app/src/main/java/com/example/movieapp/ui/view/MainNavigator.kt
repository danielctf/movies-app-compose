package com.example.movieapp.ui.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.domain.entity.Movie
import com.example.movieapp.domain.entity.MovieType
import com.example.movieapp.domain.utils.fromJson
import com.example.movieapp.domain.utils.toJson
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
    composable("${Navigation.Movies.route}/{type}") { backStackEntry ->
        backStackEntry.arguments?.getString("type")?.let { type ->
            Movies(
                type = MovieType.valueOf(type),
                goToMovieDetail = {
                    navController.navigate("${Navigation.MovieDetail.route}${it.toJson()}")
                }
            )
        }
    }
}

private fun NavGraphBuilder.addMovieDetail() {
    composable("${Navigation.MovieDetail.route}{movie}") { backStackEntry ->
        backStackEntry.arguments?.getString("movie")?.let { movie ->
            movie.fromJson<Movie>()?.let {
                MovieDetail(movie = it)
            }
        }
    }
}
