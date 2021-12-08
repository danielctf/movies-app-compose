package com.example.movieapp.ui.view.main

import com.example.movieapp.domain.entity.MovieType
import com.example.movieapp.ui.mapper.mapToUi

fun newNavigationItemsList() = listOf(MovieType.TOP_RATED, MovieType.POPULAR).mapToUi()
