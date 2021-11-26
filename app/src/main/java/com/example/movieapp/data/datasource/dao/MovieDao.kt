package com.example.movieapp.data.datasource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.movieapp.data.entity.DataMovie
import com.example.movieapp.domain.entity.MovieType
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert
    suspend fun insert(list: List<DataMovie>)

    @Query("DELETE FROM DataMovie WHERE type = :type")
    suspend fun delete(type: MovieType)

    @Query("SELECT * FROM DataMovie WHERE type = :type")
    fun getMoviesList(type: MovieType): Flow<List<DataMovie>>

    @Query("SELECT * FROM DataMovie WHERE uid = :uid")
    suspend fun getMovie(uid: String): DataMovie
}
