package com.example.movieapp.ui.entity

import androidx.annotation.StringRes
import com.example.movieapp.domain.entity.MovieType

data class MenuItem(
    @StringRes val textId: Int,
    val type: MovieType
)
