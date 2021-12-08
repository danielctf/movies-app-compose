package com.example.movieapp.ui.view.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.ui.mapper.mapToUi
import com.example.movieapp.ui.viewmodel.main.MainViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MainView() {
    val viewModel: MainViewModel = viewModel()
    val navController = rememberNavController()

    LaunchedEffect(key1 = Unit) {
        viewModel.goToMovies.collectLatest {
            navController.navigate(it) {
                // https://developer.android.com/jetpack/compose/navigation#bottom-nav
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                // Avoid multiple copies of the same destination when
                // re selecting the same item
                launchSingleTop = true
                // Restore state when re selecting a previously selected item
                restoreState = true
            }
        }
    }

    BottomNavigationView(
        navigationItemsList = viewModel.navigationItemsList.mapToUi(),
        selectedNavigationItem = viewModel.selectedNavigationItem.value,
        onNavigationItemClicked = viewModel::onNavigationItemClicked
    ) {
        MainNavHost(navController)
    }
}
