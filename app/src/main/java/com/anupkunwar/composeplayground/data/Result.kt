package com.anupkunwar.composeplayground.data

import java.lang.Exception

sealed class Result<T> {
    data class Success<R>(val data: R) : Result<R>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

fun Result<*>.isSuccess() = this is Result.Success

