package com.maiconhellmann.shared.networking

import com.maiconhellmann.shared.R
import com.maiconhellmann.shared.networking.exception.EmptyResponseBodyException
import com.maiconhellmann.shared.wrappers.StringProvider
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.internal.http2.ConnectionShutdownException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Interceptor responsável por lidar com exceções e fornecer uma mensagem de erro apropriada para elas.
 */
class OkHttpExceptionInterceptor(
    private val stringProvider: StringProvider,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        try {
            val response = chain.proceed(request)
            val body =
                response.body?.string()?.toResponseBody() ?: throw EmptyResponseBodyException()

            return response.newBuilder()
                .body(body)
                .build()
        } catch (e: Exception) {
            val msg = when (e) {
                is SocketTimeoutException -> {
                    stringProvider.getString(R.string.error_timeout)
                }
                is UnknownHostException -> {
                    stringProvider.getString(R.string.error_no_connection)
                }
                is ConnectionShutdownException -> {
                    stringProvider.getString(R.string.error_connection_shutdown)
                }
                is IOException -> {
                    stringProvider.getString(R.string.error_server_unreachable)
                }
                is EmptyResponseBodyException -> {
                    stringProvider.getString(R.string.error_empty_body)
                }
                is IllegalStateException -> {
                    "${e.message}"
                }
                else -> {
                    "${e.message}"
                }
            }

            return Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .code(999)
                .message(msg)
                .body(EMPTY_JSON.toResponseBody()).build()
        }
    }

    companion object {
        private const val EMPTY_JSON = "{}"
    }
}