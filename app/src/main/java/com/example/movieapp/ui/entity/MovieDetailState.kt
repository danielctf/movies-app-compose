package com.example.movieapp.ui.entity

import com.example.movieapp.domain.entity.Movie

sealed interface MovieDetailState {
    object Loading : MovieDetailState
    data class Content(val movie: Movie) : MovieDetailState
}
