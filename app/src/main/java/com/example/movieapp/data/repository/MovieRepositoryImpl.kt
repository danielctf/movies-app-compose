package com.example.movieapp.data.repository

import com.example.movieapp.data.datasource.MovieRemoteDataSource
import com.example.movieapp.data.datasource.dao.MovieDao
import com.example.movieapp.data.entity.DataMovie
import com.example.movieapp.data.mapper.toDomain
import com.example.movieapp.domain.entity.Movie
import com.example.movieapp.domain.entity.MovieType
import com.example.movieapp.domain.entity.Result
import com.example.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remote: MovieRemoteDataSource,
    private val local: MovieDao
) : MovieRepository {

    override fun getMoviesList(type: MovieType): Flow<List<Movie>> =
        local.getMoviesList(type).map { it.toDomain() }

    override suspend fun refreshMovies(type: MovieType): Result<Unit> =
        when (val result = getRemoteMoviesList(type)) {
            is Result.Success -> saveMoviesList(result.data, type)
            is Result.Error -> result
            else -> Result.Error(Exception("No movies found for type $type"))
        }

    private suspend fun getRemoteMoviesList(type: MovieType): Result<List<DataMovie>>? =
        when (type) {
            MovieType.TOP_RATED -> remote.getTopRatedMoviesList()
            MovieType.POPULAR -> remote.getPopularMoviesList()
            MovieType.NONE -> null
        }

    private fun saveMoviesList(list: List<DataMovie>, type: MovieType): Result<Unit> {
        local.delete(type)
        local.insert(list)
        return Result.Success(Unit)
    }
}
