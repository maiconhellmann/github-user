package com.maiconhellmann.github_user.shared.repository

import com.maiconhellmann.github_user.shared.repository.model.Repository
import com.maiconhellmann.github_user.shared.repository.model.User
import com.maiconhellmann.github_user.shared.repository.model.UserDetail
import com.maiconhellmann.github_user.shared.repository.transformer.RepositoryContractToRepositoryTransformer
import com.maiconhellmann.github_user.shared.repository.transformer.UserContractToUserTransformer
import com.maiconhellmann.github_user.shared.repository.transformer.UserDetailContractToUserDetailTransformer
import com.maiconhellmann.github_user.shared.service.UserApiService
import com.maiconhellmann.shared.networking.repository.RepositoryResult
import com.maiconhellmann.shared.networking.repository.whenSuccess

/**
 * Interface que define as operações para recuperar informações relacionadas aos usuários do GitHub.
 */
interface UserRepository {

    /**
     * Mantém a última lista de usuarios em cache para que seja possível consultar posteriormente.
     */
    val cachedUserList: List<User>

    /**
     * Recupera a lista de usuários do GitHub.
     *
     * @return [RepositoryResult] contendo a lista de usuários ou o erro ocorrido durante a recuperação.
     */
    suspend fun fetchUserList(): RepositoryResult<List<User>>

    /**
     * Recupera os repositórios de um usuário específico.
     *
     * @param login Login do usuário.
     * @return [RepositoryResult] contendo a lista de repositórios do usuário ou o erro ocorrido durante a recuperação.
     */
    suspend fun fetchUserRepositories(login: String): RepositoryResult<List<Repository>>

    /**
     * Recupera os detalhes de um usuário específico.
     *
     * @param login Login do usuário.
     * @return [RepositoryResult] contendo os detalhes do usuário ou o erro ocorrido durante a recuperação.
     */
    suspend fun fetchUserDetail(login: String): RepositoryResult<UserDetail>
}


class UserRepositoryImpl(
    private val userApiService: UserApiService,
    private val userTransformer: UserContractToUserTransformer,
    private val userDetailTransformer: UserDetailContractToUserDetailTransformer,
    private val repositoryTransformer: RepositoryContractToRepositoryTransformer,
) : UserRepository {

    private var _cachedUserList = emptyList<User>()

    override val cachedUserList: List<User>
        get() = _cachedUserList

    override suspend fun fetchUserList(): RepositoryResult<List<User>> {
        return userApiService.fetchUsers().whenSuccess {
            userTransformer.transform(it).also { result ->
                _cachedUserList = result
            }
        }
    }

    override suspend fun fetchUserRepositories(login: String): RepositoryResult<List<Repository>> {
        return userApiService.fetchUserRepositories(login).whenSuccess {
            repositoryTransformer.transform(it)
        }
    }

    override suspend fun fetchUserDetail(login: String): RepositoryResult<UserDetail> {
        return userApiService.fetchUserDetail(login).whenSuccess {
            userDetailTransformer.transform(it)
        }
    }
}