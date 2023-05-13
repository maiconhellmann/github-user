package com.maiconhellmann.github_user.shared.service.model

import com.squareup.moshi.Json

/**
 * Contrato de repositório obtido do servidor.
 * Esta classe representa as informações dos detalhes de um usuário obtido do servidor do GitHub.
 *
 * */
class UserDetailContract(
    val login: String,
    val id: Long,
    @Json(name = "avatar_url") val avatarURL: String?,
    val name: String?,
    val company: String?,
    val followers: Int?,
    val following: Int?,
)