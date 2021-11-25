package com.example.movieapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movieapp.domain.entity.MovieType
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.UUID

@Entity
@JsonClass(generateAdapter = true)
data class DataMovie(
    @PrimaryKey
    val uid: String = UUID.randomUUID().toString(),
    val overview: String,
    val title: String,
    @Json(name = "release_date")
    val releaseDate: String,
    @Json(name = "vote_average")
    val averageVote: Double,
    @Json(name = "poster_path")
    val posterPath: String?,
    var type: MovieType = MovieType.NONE
)
