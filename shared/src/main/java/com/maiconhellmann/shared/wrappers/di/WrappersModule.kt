package com.maiconhellmann.shared.wrappers.di

import com.maiconhellmann.shared.wrappers.StringProvider
import com.maiconhellmann.shared.wrappers.StringProviderImpl
import org.koin.dsl.module

/**
 * Provide dependency injection to all wrapper
 */
val wrappersModule = module {
    single<StringProvider> { StringProviderImpl(get()) }
}