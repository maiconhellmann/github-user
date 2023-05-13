package com.maiconhellmann.shared.networking.repository

import retrofit2.Response
import kotlin.Error

/**
 * RepositoryResult encapsulates a result type in order to make it easier to handle api service results.
 */
sealed class RepositoryResult<T> {
    data class Success<T>(val result: T) : RepositoryResult<T>()
    data class Error<Nothing>(val errorCode: Int?, val errorMessage: String?) : RepositoryResult<Nothing>()

    /**
     * Execute a block of code when the result is a success
     */
    fun whenSuccess(block: (T) -> Unit): RepositoryResult<T> {
        if (this is Success) {
            block(this.result)
        }
        return this
    }

    /**
     * Execute a block of code when the result is an error
     */
    fun whenError(block: (String) -> Unit): RepositoryResult<T> {
        if (this is Error) {
            block(this.errorMessage.orEmpty())
        }
        return this
    }

    fun isError() = this is Error
    fun isSuccess() = this is Success
}

/**
 * Helper extension function to run a block of code when the result was a success.
 * Returns either a [RepositoryResult.EmptyBody] or a [RepositoryResult.Error] otherwise.
 */
fun <T, D> Response<D>.whenSuccess(block: (D) -> T): RepositoryResult<T> {
    if (this.isSuccessful) {
        this.body()?.also { body ->
            return RepositoryResult.Success(block(body))
        }
    }
    return RepositoryResult.Error(this.code(), this.message())
}