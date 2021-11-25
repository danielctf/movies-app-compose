package com.example.movieapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieapp.data.datasource.dao.MovieDao
import com.example.movieapp.data.entity.DataMovie

@Database(entities = [DataMovie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}
