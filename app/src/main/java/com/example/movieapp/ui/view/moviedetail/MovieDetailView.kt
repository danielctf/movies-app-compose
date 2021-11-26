package com.example.movieapp.ui.view.moviedetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.domain.entity.Movie
import com.example.movieapp.ui.entity.MovieDetailState
import com.example.movieapp.ui.theme.imageSize
import com.example.movieapp.ui.theme.regularMargin
import com.example.movieapp.ui.theme.regularText
import com.example.movieapp.ui.view.movie.*
import com.example.movieapp.ui.viewmodel.MovieDetailViewModel

@Composable
fun MovieDetail() {
    val viewModel: MovieDetailViewModel = hiltViewModel()
    val state = viewModel.state.value
    if (state is MovieDetailState.Content) {
        MovieDetailContent(movie = state.movie)
    }
}

@Composable
fun MovieDetailContent(movie: Movie) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(regularMargin),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MovieItemImage(imageUrl = movie.imageUrl, size = imageSize)
        Spacer(modifier = Modifier.size(regularMargin))
        MovieItemTitle(title = movie.title, textAlign = TextAlign.Center)
        MovieItemReleaseDate(releaseDate = movie.releaseDate)
        MovieItemAverageVote(averageVote = movie.averageVote.toString())
        Spacer(modifier = Modifier.size(regularMargin))
        OverView(text = movie.overview)
    }
}

@Composable
fun OverView(text: String) {
    Text(
        text = text,
        style = TextStyle(fontSize = regularText, color = Color.Gray),
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
fun MovieDetailContentPreview() {
    MovieDetailContent(movie = newMovie())
}
