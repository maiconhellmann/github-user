package com.maiconhellmann.github_user.shared.di

import com.maiconhellmann.github_user.shared.repository.UserRepository
import com.maiconhellmann.github_user.shared.repository.UserRepositoryImpl
import com.maiconhellmann.github_user.shared.repository.transformer.*
import com.maiconhellmann.github_user.shared.service.UserApiService
import com.maiconhellmann.shared.networking.ApiUrlEnum
import org.koin.dsl.module
import retrofit2.Retrofit

val userSharedModule = module {
    single<UserApiService> {
        get<Retrofit>(ApiUrlEnum.GITHUB).create(UserApiService::class.java)
    }
    factory<UserContractToUserTransformer> { UserContractToUserTransformerImpl() }
    factory<RepositoryContractToRepositoryTransformer> { RepositoryContractToRepositoryTransformerImpl() }
    factory<UserDetailContractToUserDetailTransformer> { UserDetailContractToUserDetailTransformerImpl() }
    single<UserRepository> { UserRepositoryImpl(get(), get(), get(), get()) }
}