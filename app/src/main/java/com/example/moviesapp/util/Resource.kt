package com.example.moviesapp.util

sealed class Resource<out T>(val data: T?, val message: String?) {

    class Success<T>(data: T) : Resource<T>(data, null)
    class Error<T>(message: String?) : Resource<T>(null, message)
}


