package com.example.movieapp.ui.view.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.R
import com.example.movieapp.domain.entity.Movie
import com.example.movieapp.ui.viewmodel.MoviesViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun Movies(goToMovieDetail: (Movie) -> Unit) {
    val viewModel: MoviesViewModel = hiltViewModel()

    LaunchedEffect(key1 = Unit) {
        viewModel.goToMovieDetail.collectLatest { goToMovieDetail(it) }
    }

    ErrorDialog(
        show = viewModel.showError.value,
        onConfirmedClicked = viewModel::onErrorConfirmed
    )

    MoviesContent(
        moviesList = viewModel.filteredMoviesList.value,
        onMovieClicked = viewModel::onMovieClicked,
        searchText = viewModel.searchText.value,
        onSearchTextChanged = viewModel::onSearchTextChanged
    )
}

@Composable
fun ErrorDialog(show: Boolean, onConfirmedClicked: () -> Unit) {
    if (show) {
        AlertDialog(
            onDismissRequest = onConfirmedClicked,
            title = { Text(text = stringResource(id = R.string.error)) },
            text = { Text(text = stringResource(id = R.string.error_message)) },
            confirmButton = {
                Button(onClick = onConfirmedClicked) {
                    Text(text = stringResource(id = R.string.ok))
                }
            }
        )
    }
}

@Composable
fun MoviesContent(
    moviesList: List<Movie>,
    onMovieClicked: (Movie) -> Unit,
    searchText: String,
    onSearchTextChanged: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar(searchText = searchText, onSearchTextChanged = onSearchTextChanged)
        LazyColumn {
            items(moviesList) {
                MovieItem(movie = it, onMovieClicked = onMovieClicked)
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    searchText: String,
    onSearchTextChanged: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        value = searchText,
        onValueChange = onSearchTextChanged,
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() })
    )
}

@Preview
@Composable
fun MoviesContentPreview() {
    MoviesContent(
        moviesList = listOf(newMovie(), newMovie()),
        onMovieClicked = { },
        searchText = "",
        onSearchTextChanged = { }
    )
}
