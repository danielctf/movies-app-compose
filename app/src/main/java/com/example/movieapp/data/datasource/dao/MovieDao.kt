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
    fun insert(list: List<DataMovie>)

    @Query("DELETE FROM DataMovie WHERE type = :type")
    fun delete(type: MovieType)

    @Query("SELECT * FROM DataMovie WHERE type = :type")
    fun getMoviesList(type: MovieType): Flow<List<DataMovie>>
}
