package com.maiconhellmann.github_user.di

import com.maiconhellmann.github_user.feature.detail.di.userDetailModule
import com.maiconhellmann.github_user.feature.list.di.userListModule
import com.maiconhellmann.github_user.shared.di.userSharedModule

val userModule = userDetailModule + userListModule + userSharedModule