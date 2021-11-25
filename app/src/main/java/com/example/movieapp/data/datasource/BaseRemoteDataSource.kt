package com.example.movieapp.data.datasource

import com.example.movieapp.domain.entity.Result

open class BaseRemoteDataSource {

    protected suspend fun <T> getResult(call: suspend () -> T): Result<T> = try {
        Result.Success(call())
    } catch (exception: Exception) {
        Result.Error(exception)
    }
}
