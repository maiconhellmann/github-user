package com.maiconhellmann.github_user.shared.service.model

import com.squareup.moshi.Json

data class RepositoryContract(
    val id: Long?,
    val name: String?,
    val description: String?,
    @Json(name = "forks_count") val forksCount: Long?,
    @Json(name = "forks_url") val forksUrl: String?,
    @Json(name = "open_issues") val openIssues: Long?,
    @Json(name = "issues_url") val issuesUrl: String?,
    @Json(name = "stargazers_count") val stars: Long?,
    val url: String?,
    @Json(name = "collaborators_url") val collaboratorsUrl: String?,
    @Json(name = "branches_url") val branchesUrl: String?,
    @Json(name = "contributors_url") val contributorsUrl: String?,
    @Json(name = "pulls_url") val pullsUrl: String?,
    @Json(name = "homepage") val homepage: String?,
)