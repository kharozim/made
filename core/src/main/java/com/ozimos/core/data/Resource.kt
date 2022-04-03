package com.ozimos.core.data

sealed class Resourse<T>(val data: T? = null, val msg: String? = null) {
    class Success<T>(data: T) : Resourse<T>(data)
    class Loading<T>(data: T? = null) : Resourse<T>(data)
    class Error<T>(message: String, data: T? = null) : Resourse<T>(data, message)
}