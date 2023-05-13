package com.maiconhellmann.github

import android.app.Application
import com.maiconhellmann.github_user.di.userModule
import com.maiconhellmann.shared.networking.di.networkModule
import com.maiconhellmann.shared.wrappers.di.wrappersModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Responsável por inicializar o framework de injeção de dependência Koin.
 * Ela é executada no início do ciclo de vida do aplicativo e configura os módulos de injeção de dependência usando o Koin.
 */
class GithubApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(
                wrappersModule,
                networkModule,
            ).modules(userModule)
        }
    }
}