package com.maiconhellmann.github_user.shared.repository.transformer

import com.maiconhellmann.github_user.shared.repository.model.UserDetail
import com.maiconhellmann.github_user.shared.service.model.UserDetailContract
import com.maiconhellmann.shared.extensions.orZero

interface UserDetailContractToUserDetailTransformer {
    fun transform(contract: UserDetailContract): UserDetail
}

class UserDetailContractToUserDetailTransformerImpl : UserDetailContractToUserDetailTransformer {
    override fun transform(contract: UserDetailContract): UserDetail {
        return UserDetail(
            login = contract.login,
            id = contract.id,
            avatarURL = contract.avatarURL.orEmpty(),
            name = contract.name.orEmpty(),
            company = contract.company.orEmpty(),
            followers = contract.followers.orZero(),
            following = contract.following.orZero(),
        )
    }

}