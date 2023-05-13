package com.maiconhellmann.github

import android.app.Application
import com.maiconhellmann.github_user.di.userModule
import com.maiconhellmann.shared.networking.di.networkModule
import com.maiconhellmann.shared.wrappers.di.wrappersModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

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