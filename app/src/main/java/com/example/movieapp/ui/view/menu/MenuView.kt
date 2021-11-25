package com.example.movieapp.ui.view.menu

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movieapp.domain.entity.MovieType
import com.example.movieapp.ui.entity.MenuItem
import com.example.movieapp.ui.theme.regularMargin
import com.example.movieapp.ui.viewmodel.MenuViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun Menu(goToMovies: (MovieType) -> Unit) {
    val viewModel: MenuViewModel = viewModel()

    LaunchedEffect(key1 = Unit) {
        viewModel.goToMovies.collectLatest { goToMovies(it) }
    }

    MenuContent(
        menuItemsList = viewModel.menuItemsList,
        onMovieTypeClicked = viewModel::onMovieTypeClicked
    )
}

@Composable
fun MenuContent(
    menuItemsList: List<MenuItem>,
    onMovieTypeClicked: (MovieType) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(menuItemsList) { menuItem ->
                MenuItem(
                    textId = menuItem.textId,
                    onClicked = { onMovieTypeClicked(menuItem.type) }
                )
            }
        }
    }
}

@Composable
fun MenuItem(@StringRes textId: Int, onClicked: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .clickable(onClick = onClicked)
    ) {
        Text(
            text = stringResource(id = textId),
            modifier = Modifier.padding(regularMargin)
        )
        Divider()
    }
}

@Preview
@Composable
fun MenuContentPreview() {
    MenuContent(
        menuItemsList = newMenuItemsList(),
        onMovieTypeClicked = { }
    )
}
