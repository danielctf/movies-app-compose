package com.example.movieapp.data.di

import com.example.movieapp.data.repository.MovieRepositoryImpl
import com.example.movieapp.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class MovieModule {

    @Binds
    abstract fun bindsMovieRepository(repository: MovieRepositoryImpl): MovieRepository
}
