package com.maiconhellmann.github_user.shared.repository.model

data class UserDetail(
    val login: String,
    val id: Long,
    val avatarURL: String,
    val name: String,
    val company: String,
    val followers: Int,
    val following: Int,
)