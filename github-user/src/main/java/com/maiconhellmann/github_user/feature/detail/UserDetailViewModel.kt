package com.maiconhellmann.github_user.feature.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maiconhellmann.github_user.feature.detail.component.UserRepositoryCardView
import com.maiconhellmann.github_user.shared.repository.UserRepository
import com.maiconhellmann.github_user.shared.repository.model.UserDetail
import com.maiconhellmann.shared.R
import com.maiconhellmann.shared.components.ErrorStateView
import com.maiconhellmann.shared.viewmodel.ViewState
import com.maiconhellmann.shared.wrappers.StringProvider
import kotlinx.coroutines.launch

class UserDetailViewModel(
    private val login: String,
    private val repository: UserRepository,
    private val stringProvider: StringProvider,
) : ViewModel() {

    val userDetailViewState = MutableLiveData<ViewState<UserDetail, ErrorStateView.UiModel>>()
    val repositoriesViewState = MutableLiveData<ViewState<List<UserRepositoryCardView.UiModel>, ErrorStateView.UiModel>>()
    val navigation = MutableLiveData<UiNav>()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            userDetailViewState.postValue(ViewState.Loading)
            repositoriesViewState.postValue(ViewState.Loading)

            repository.fetchUserRepositories(login).whenSuccess { repositoryList ->
                val repositoryUiModel = repositoryList.map { repo ->
                    UserRepositoryCardView.UiModel(
                        id = repo.id,
                        name = repo.name,
                        description = repo.description,
                        stars = repo.stars.toString(),
                        forks = repo.forksCount.toString(),
                        openIssues = repo.openIssues.toString(),
                    )
                }
                repositoriesViewState.postValue(ViewState.Success(repositoryUiModel))
            }.whenError {
                repositoriesViewState.postValue(
                    ViewState.Error(
                        ErrorStateView.UiModel(
                            icon = R.drawable.ic_baseline_sentiment_very_dissatisfied_24,
                            text = stringProvider.getString(com.maiconhellmann.github_user.R.string.error_api_call),
                        )
                    )
                )
            }

            repository.fetchUserDetail(login).whenSuccess { userDetail ->
                userDetailViewState.postValue(ViewState.Success(userDetail))
            }.whenError {
                userDetailViewState.postValue(
                    ViewState.Error(
                        ErrorStateView.UiModel(
                            icon = R.drawable.ic_baseline_sentiment_very_dissatisfied_24,
                            text = stringProvider.getString(com.maiconhellmann.github_user.R.string.error_api_call),
                        )
                    )
                )
            }
        }
    }

    fun onErrorViewClick() {
        loadData()
    }

    fun onBackPressed() {
        navigation.postValue(UiNav.NavigateToUserList)
    }

    sealed class UiNav {
        object NavigateToUserList : UiNav()
    }
}