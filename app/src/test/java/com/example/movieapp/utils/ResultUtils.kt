package com.example.movieapp.utils

import com.example.movieapp.domain.entity.Result

fun <T> Result<T>.getData(): T = (this as Result.Success).data
fun Result<*>.getError(): Exception = (this as Result.Error).exception
