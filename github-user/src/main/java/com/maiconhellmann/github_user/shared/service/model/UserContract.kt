package com.maiconhellmann.github_user.shared.service.model

import com.squareup.moshi.Json

/**
 * Contrato de repositório obtido do servidor.
 * Esta classe representa as informações de um usuário obtido do servidor do GitHub.
 * */
class UserContract(
    val login: String,
    val id: Long,

    @Json(name = "node_id")
    val nodeID: String?,

    @Json(name = "avatar_url")
    val avatarURL: String?,

    @Json(name = "gravatar_id")
    val gravatarID: String?,

    val url: String?,

    @Json(name = "html_url")
    val htmlURL: String?,

    @Json(name = "followers_url")
    val followersURL: String?,

    @Json(name = "following_url")
    val followingURL: String?,

    @Json(name = "gists_url")
    val gistsURL: String?,

    @Json(name = "starred_url")
    val starredURL: String?,

    @Json(name = "subscriptions_url")
    val subscriptionsURL: String?,

    @Json(name = "organizations_url")
    val organizationsURL: String?,

    @Json(name = "repos_url")
    val reposURL: String?,

    @Json(name = "events_url")
    val eventsURL: String?,

    @Json(name = "received_events_url")
    val receivedEventsURL: String?,

    val type: String?,

    @Json(name = "site_admin")
    val siteAdmin: Boolean?
)