package com.example.movieapp.ui.entity.main

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.movieapp.domain.entity.MovieType

data class NavigationItem(
    @StringRes val textId: Int,
    val icon: ImageVector,
    val type: MovieType
)
