package com.maiconhellmann.github_user.shared.repository.transformer

import com.maiconhellmann.github_user.shared.repository.model.User
import com.maiconhellmann.github_user.shared.service.model.UserContract
import com.maiconhellmann.shared.extensions.orFalse
import com.maiconhellmann.shared.extensions.orZero

interface UserContractToUserTransformer {
    fun transform(contractList: List<UserContract>): List<User>
}

class UserContractToUserTransformerImpl : UserContractToUserTransformer {
    override fun transform(contractList: List<UserContract>): List<User> {
        return contractList.map {
            User(
                login = it.login,
                id = it.id.orZero(),
                nodeID = it.nodeID.orEmpty(),
                avatarURL = it.avatarURL.orEmpty(),
                gravatarID = it.gravatarID.orEmpty(),
                url = it.url.orEmpty(),
                htmlURL = it.htmlURL.orEmpty(),
                followersURL = it.followersURL.orEmpty(),
                followingURL = it.followingURL.orEmpty(),
                gistsURL = it.gistsURL.orEmpty(),
                starredURL = it.starredURL.orEmpty(),
                subscriptionsURL = it.subscriptionsURL.orEmpty(),
                organizationsURL = it.organizationsURL.orEmpty(),
                reposURL = it.reposURL.orEmpty(),
                eventsURL = it.eventsURL.orEmpty(),
                receivedEventsURL = it.receivedEventsURL.orEmpty(),
                type = it.type.orEmpty(),
                siteAdmin = it.siteAdmin.orFalse()
            )
        }
    }
}