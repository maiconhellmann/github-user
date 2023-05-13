package com.maiconhellmann.shared.networking.di

import com.maiconhellmann.shared.networking.ApiUrlEnum
import com.maiconhellmann.shared.networking.RetrofitHelper
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module

/**
 * Provide all instances required to create a restful HttpClient.
 */
val networkModule = module {
    single {
        Moshi.Builder()
            // automatically create adapters in runtime
            .add(KotlinJsonAdapterFactory())
            .build()
    }
    single(ApiUrlEnum.GITHUB) {
        RetrofitHelper.createRetrofitClient(ApiUrlEnum.GITHUB.baseUrl, get(), get())
    }
}