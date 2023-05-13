package com.maiconhellmann.shared.viewmodel

/**
 * Representa a ViewState para ser observado por Activities/Fragments.
 */
sealed class ViewState<out T, out E> {
    object Loading : ViewState<Nothing, Nothing>()
    data class Success<T>(val state: T) : ViewState<T, Nothing>()
    data class Error<E>(val error: E) : ViewState<Nothing, E>()

    fun isLoading() = this is Loading
    fun isError() = this is Error
}