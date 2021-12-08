package com.example.movieapp.ui.viewmodel.movies

import androidx.compose.runtime.State
import com.example.movieapp.domain.entity.Movie
import kotlinx.coroutines.flow.SharedFlow

interface MoviesViewModelDelegate {

    val filteredMoviesList: State<List<Movie>>
    val searchText: State<String>
    val showError: State<Boolean>
    val goToMovieDetail: SharedFlow<String>

    fun onMovieClicked(uid: String)
    fun onSearchTextChanged(text: String)
    fun onErrorConfirmed()
}
