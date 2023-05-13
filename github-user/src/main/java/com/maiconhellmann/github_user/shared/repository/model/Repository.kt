package com.maiconhellmann.github_user.shared.repository.model


data class Repository(
    val id: Long,
    val name: String,
    val description: String,
    val forksCount: Long,
    val forksUrl: String,
    val openIssues: Long,
    val issuesUrl: String,
    val stars: Long,
    val url: String,
    val collaboratorsUrl: String,
    val branchesUrl: String,
    val contributorsUrl: String,
    val pullsUrl: String,
    val homepage: String,
)