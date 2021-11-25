package com.example.movieapp.ui.view.movie

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import coil.compose.rememberImagePainter
import com.example.movieapp.domain.entity.Movie
import com.example.movieapp.ui.theme.*

@Composable
fun MovieItem(
    movie: Movie,
    onMovieClicked: (Movie) -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onMovieClicked(movie) }
                .padding(horizontal = smallMargin, vertical = regularMargin)
        ) {
            MovieItemImage(imageUrl = movie.thumbnailImageUrl, size = thumbnailSize)
            Column {
                MovieItemTitle(title = movie.title)
                MovieItemReleaseDate(releaseDate = movie.releaseDate)
                MovieItemAverageVote(averageVote = movie.averageVote.toString())
            }
        }
        Divider()
    }
}

@Composable
fun MovieItemImage(imageUrl: String, size: Dp) {
    Image(
        painter = rememberImagePainter(imageUrl),
        contentDescription = null,
        modifier = Modifier.size(size)
    )
}

@Composable
fun MovieItemTitle(title: String, textAlign: TextAlign = TextAlign.Left) {
    Text(text = title, style = TextStyle(fontSize = bigText), textAlign = textAlign)
}

@Composable
fun MovieItemReleaseDate(releaseDate: String) {
    Text(
        text = releaseDate,
        style = TextStyle(fontSize = regularText, color = Color.Gray)
    )
}

@Composable
fun MovieItemAverageVote(averageVote: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Default.Star,
            modifier = Modifier.size(smallSize),
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(extraSmallMargin))
        Text(
            text = averageVote,
            style = TextStyle(fontSize = regularText, color = Color.Gray)
        )
    }
}

@Preview
@Composable
fun MovieItemPreview() {
    MovieItem(movie = newMovie(), onMovieClicked = { })
}
