package com.example.movieapp.ui.view.main

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieapp.domain.entity.MovieType
import com.example.movieapp.ui.entity.main.NavigationItem

@Composable
fun BottomNavigationView(
    navigationItemsList: List<NavigationItem>,
    selectedNavigationItem: MovieType,
    onNavigationItemClicked: (MovieType) -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomBar(
                navigationItemsList = navigationItemsList,
                selectedNavigationItem = selectedNavigationItem,
                onNavigationItemClicked = onNavigationItemClicked
            )
        }
    ) {
        Surface(modifier = Modifier.padding(it)) {
            content()
        }
    }
}

@Composable
fun BottomBar(
    navigationItemsList: List<NavigationItem>,
    selectedNavigationItem: MovieType,
    onNavigationItemClicked: (MovieType) -> Unit
) {
    BottomNavigation {
        navigationItemsList.forEach {
            BottomBarItem(
                navigationItem = it,
                selectedNavigationItem = selectedNavigationItem,
                onNavigationItemClicked = onNavigationItemClicked
            )
        }
    }
}

@Composable
fun RowScope.BottomBarItem(
    navigationItem: NavigationItem,
    selectedNavigationItem: MovieType,
    onNavigationItemClicked: (MovieType) -> Unit
) {
    BottomNavigationItem(
        label = { Text(text = stringResource(id = navigationItem.textId)) },
        icon = { Icon(imageVector = navigationItem.icon, contentDescription = null) },
        onClick = { onNavigationItemClicked(navigationItem.type) },
        selected = navigationItem.type == selectedNavigationItem
    )
}

@Preview
@Composable
fun BottomNavigationViewPreview() {
    val navigationItemsList = newNavigationItemsList()
    BottomNavigationView(
        navigationItemsList = navigationItemsList,
        selectedNavigationItem = navigationItemsList.first().type,
        onNavigationItemClicked = { },
        content = { }
    )
}
