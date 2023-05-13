package com.maiconhellmann.github_user.feature.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maiconhellmann.github_user.feature.detail.component.UserRepositoryCardView
import com.maiconhellmann.github_user.shared.repository.UserRepository
import com.maiconhellmann.shared.R
import com.maiconhellmann.shared.components.ErrorStateView
import com.maiconhellmann.shared.viewmodel.ViewState
import com.maiconhellmann.shared.wrappers.StringProvider
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class UserDetailViewModel(
    private val login: String,
    private val repository: UserRepository,
    private val stringProvider: StringProvider,
) : ViewModel() {

    val viewStateLiveData = MutableLiveData<ViewState<State, ErrorStateView.UiModel>>()
    val navigation = MutableLiveData<UiNav>()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            viewStateLiveData.postValue(ViewState.Loading)

            // load both async
            val repositoryDeferredResult = async { repository.fetchUserRepositories(login) }
            val userDetailDeferredResult = async { repository.fetchUserDetail(login) }

            // wait for both to finish
            val repositoryResult = repositoryDeferredResult.await()
            val userDetailResult = userDetailDeferredResult.await()

            // since there is only one liveData this will hold the new state to avoid many live data calls
            var newState = State()

            repositoryResult.whenSuccess { repositoryList ->
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
                newState = newState.copy(
                    repositoryUiModelList = repositoryUiModel
                )
            }

            userDetailResult.whenSuccess { userDetail ->
                newState = newState.copy(
                    avatarUrl = userDetail.avatarURL,
                    name = userDetail.name,
                    followers = userDetail.followers.toString(),
                    following = userDetail.following.toString(),
                    company = userDetail.company,
                    login = userDetail.login,
                )
            }

            if (userDetailResult.isError() && repositoryResult.isError()) {
                viewStateLiveData.postValue(
                    ViewState.Error(
                        ErrorStateView.UiModel(
                            icon = R.drawable.ic_baseline_sentiment_very_dissatisfied_24,
                            text = stringProvider.getString(com.maiconhellmann.github_user.R.string.error_api_call),
                        )
                    )
                )
            } else {
                viewStateLiveData.postValue(ViewState.Success(newState))
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

    data class State(
        val avatarUrl: String = "",
        val login: String = "",
        val name: String = "",
        val company: String = "",
        val followers: String = "",
        val following: String = "",
        val repositoryUiModelList: List<UserRepositoryCardView.UiModel> = emptyList()
    )
}