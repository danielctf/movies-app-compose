package com.example.movieapp.ui.viewmodel.movies

import com.example.movieapp.domain.entity.MovieType

interface MoviesViewModelHandler : MoviesViewModelDelegate {

    fun setType(type: MovieType)
}
