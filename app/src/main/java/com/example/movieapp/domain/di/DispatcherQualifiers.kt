package com.example.movieapp.domain.di

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher
