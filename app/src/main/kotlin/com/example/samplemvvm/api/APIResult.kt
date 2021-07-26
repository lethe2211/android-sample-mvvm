package com.example.samplemvvm.api

/**
 * Wrapper for the response from API.
 */
sealed class APIResult<out T> {

    object Loading: APIResult<Nothing>()

    data class Success<out T>(
        val value: T
    ) : APIResult<T>()

    data class Failure<out T>(
        val error: Throwable
    ) : APIResult<T>()

    val isLoading get() = this is Loading
    val isFailure get() = this is Failure
    val valueOrNull get() = (this as? Success)?.value
}