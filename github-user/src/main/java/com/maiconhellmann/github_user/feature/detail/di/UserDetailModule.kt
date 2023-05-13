package com.maiconhellmann.github_user.feature.detail.di

import com.maiconhellmann.github_user.feature.detail.UserDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userDetailModule = module {
    viewModel { (login: String) ->
        UserDetailViewModel(login, get(), get())
    }
}