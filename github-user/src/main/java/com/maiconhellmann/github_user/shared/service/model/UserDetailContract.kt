package com.maiconhellmann.github_user.shared.service.model

import com.squareup.moshi.Json

class UserDetailContract(
    val login: String,
    val id: Long,
    @Json(name = "avatar_url") val avatarURL: String?,
    val name: String?,
    val company: String?,
    val followers: Int?,
    val following: Int?,
)