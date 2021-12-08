package com.example.movieapp.ui.di

import com.example.movieapp.ui.viewmodel.movies.MoviesViewModelHandler
import com.example.movieapp.ui.viewmodel.movies.MoviesViewModelHandlerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {

    @Binds
    abstract fun bindsMoviesViewModelHandler(
        handler: MoviesViewModelHandlerImpl
    ): MoviesViewModelHandler
}
