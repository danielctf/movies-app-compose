package com.example.movieapp.ui.viewmodel.movies

import androidx.lifecycle.ViewModel
import com.example.movieapp.domain.entity.MovieType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    handler: MoviesViewModelHandler
) : ViewModel(),
    MoviesViewModelDelegate by handler {

    init {
        handler.setType(MovieType.POPULAR)
    }
}
