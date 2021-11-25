package com.example.movieapp.data.datasource.service

import com.example.movieapp.BuildConfig
import com.example.movieapp.data.entity.DataMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("$MOVIE_ENDPOINT/top_rated")
    suspend fun getTopRatedMoviesList(
        @Query(API_KEY) apiKey: String = BuildConfig.API_KEY_VALUE
    ): DataMovieResponse

    @GET("$MOVIE_ENDPOINT/popular")
    suspend fun getPopularMoviesList(
        @Query(API_KEY) apiKey: String = BuildConfig.API_KEY_VALUE
    ): DataMovieResponse

    private companion object {
        const val MOVIE_ENDPOINT = "movie"
        const val API_KEY = "api_key"
    }
}
