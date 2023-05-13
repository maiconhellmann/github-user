package com.maiconhellmann.github_user.feature.list.di

import com.maiconhellmann.github_user.feature.list.UserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userListModule = module {
    viewModel { UserListViewModel(get()) }
}