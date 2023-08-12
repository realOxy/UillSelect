package com.oxy.uillselect.core.wrapper

sealed class Resource<in T> {
    data object Loading : Resource<Nothing>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Failure(val message: String) : Resource<Nothing>()
}