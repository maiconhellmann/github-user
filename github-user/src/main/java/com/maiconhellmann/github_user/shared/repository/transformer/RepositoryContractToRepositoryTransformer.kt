package com.maiconhellmann.github_user.shared.repository.transformer

import com.maiconhellmann.github_user.shared.repository.model.Repository
import com.maiconhellmann.github_user.shared.service.model.RepositoryContract
import com.maiconhellmann.shared.extensions.orZero

interface RepositoryContractToRepositoryTransformer {
    fun transform(contractList: List<RepositoryContract>): List<Repository>
}

class RepositoryContractToRepositoryTransformerImpl : RepositoryContractToRepositoryTransformer {
    override fun transform(contractList: List<RepositoryContract>): List<Repository> {
        return contractList.map { contract ->
            Repository(
                id = contract.id.orZero(),
                name = contract.name.orEmpty(),
                description = contract.description.orEmpty(),
                forksCount = contract.forksCount.orZero(),
                forksUrl = contract.forksUrl.orEmpty(),
                openIssues = contract.openIssues.orZero(),
                issuesUrl = contract.issuesUrl.orEmpty(),
                stars = contract.stars.orZero(),
                url = contract.url.orEmpty(),
                collaboratorsUrl = contract.collaboratorsUrl.orEmpty(),
                branchesUrl = contract.branchesUrl.orEmpty(),
                contributorsUrl = contract.contributorsUrl.orEmpty(),
                pullsUrl = contract.pullsUrl.orEmpty(),
                homepage = contract.homepage.orEmpty(),
            )
        }
    }
}