package com.example.movieapp.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataMovieResponse(
    @Json(name = "results")
    val moviesList: List<DataMovie>
)
