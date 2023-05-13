package com.maiconhellmann.shared.networking

import com.maiconhellmann.shared.wrappers.StringProvider
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Cria um cliente Retrofit para uma URL base específica.
 */
object RetrofitHelper {
    fun createRetrofitClient(
        baseUrl: String,
        moshi: Moshi,
        stringProvider: StringProvider
    ): Retrofit {
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .addInterceptor(OkHttpExceptionInterceptor(stringProvider))
                    .build()
            )
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
}