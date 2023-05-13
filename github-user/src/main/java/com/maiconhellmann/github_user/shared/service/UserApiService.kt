package com.maiconhellmann.github_user.shared.service

import com.maiconhellmann.github_user.shared.service.model.RepositoryContract
import com.maiconhellmann.github_user.shared.service.model.UserContract
import com.maiconhellmann.github_user.shared.service.model.UserDetailContract
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Interface que define as chamadas de API relacionadas aos usuários do GitHub.
 */
interface UserApiService {

    /**
     * Recupera a lista de usuários do GitHub.
     *
     * @return [Response] contendo a lista de usuários ou um erro ocorrido durante a recuperação.
     */
    @GET("users")
    suspend fun fetchUsers(): Response<List<UserContract>>

    /**
     * Recupera os repositórios de um usuário específico.
     *
     * @param login Login do usuário.
     * @return [Response] contendo a lista de repositórios do usuário ou um erro ocorrido durante a recuperação.
     */
    @GET("users/{login}/repos")
    suspend fun fetchUserRepositories(@Path("login") login: String): Response<List<RepositoryContract>>

    /**
     * Recupera os detalhes de um usuário específico.
     *
     * @param login Login do usuário.
     * @return [Response] contendo os detalhes do usuário ou um erro ocorrido durante a recuperação.
     */
    @GET("users/{login}")
    suspend fun fetchUserDetail(@Path("login") login: String): Response<UserDetailContract>
}
