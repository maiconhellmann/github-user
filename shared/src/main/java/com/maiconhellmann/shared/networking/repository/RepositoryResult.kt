package com.maiconhellmann.shared.networking.repository

import retrofit2.Response
import kotlin.Error

/**
 * RepositoryResult encapsula um tipo de resultado para facilitar o manuseio dos resultados de serviços de API.
 */
sealed class RepositoryResult<T> {
    data class Success<T>(val result: T) : RepositoryResult<T>()
    data class Error<Nothing>(val errorCode: Int?, val errorMessage: String?) : RepositoryResult<Nothing>()

    /**
     * Executa um block de código quando o resultado é sucesso
     */
    fun whenSuccess(block: (T) -> Unit): RepositoryResult<T> {
        if (this is Success) {
            block(this.result)
        }
        return this
    }

    /**
     * xecuta um block de código quando o resultado é erro
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
 * Função de extensão auxiliar para executar um bloco de código quando o resultado for bem-sucedido.
 * Retorna um [RepositoryResult.EmptyBody] ou um [RepositoryResult.Error] caso contrário.
 */
fun <T, D> Response<D>.whenSuccess(block: (D) -> T): RepositoryResult<T> {
    if (this.isSuccessful) {
        this.body()?.also { body ->
            return RepositoryResult.Success(block(body))
        }
    }
    return RepositoryResult.Error(this.code(), this.message())
}