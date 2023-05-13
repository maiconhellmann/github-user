package com.maiconhellmann.github_user.shared.service

import com.maiconhellmann.github_user.shared.service.model.RepositoryContract
import com.maiconhellmann.github_user.shared.service.model.UserContract
import com.maiconhellmann.github_user.shared.service.model.UserDetailContract
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApiService {

    @GET("users")
    suspend fun fetchUsers(): Response<List<UserContract>>

    @GET("users/{login}/repos")
    suspend fun fetchUserRepositories(@Path("login") login: String): Response<List<RepositoryContract>>

    @GET("users/{login}")
    suspend fun fetchUserDetail(@Path("login") login: String): Response<UserDetailContract>
}