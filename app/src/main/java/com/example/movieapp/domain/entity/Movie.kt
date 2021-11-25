package com.example.movieapp.domain.entity

data class Movie(
    val overview: String,
    val title: String,
    val releaseDate: String,
    val averageVote: Double,
    val posterPath: String?,
) {

    val thumbnailImageUrl = "${BASE_IMAGE_URL}w154$posterPath"
    val imageUrl = "${BASE_IMAGE_URL}w500$posterPath"

    private companion object {
        // Hardcoding base url and dimensions just for simplicity. Probably a cleaner approach would
        // be to query the /configuration API for these values
        const val BASE_IMAGE_URL = "http://image.tmdb.org/t/p/"
    }
}
