package com.example.movieapp.domain.utils

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

inline fun <reified T> T.toJson(): String = getAdapter<T>().toJson(this)

inline fun <reified T> String.fromJson(): T? = getAdapter<T>().fromJson(this)

inline fun <reified T> getAdapter(): JsonAdapter<T> = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
    .adapter(T::class.java)
