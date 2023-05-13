package com.maiconhellmann.github_user.feature.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maiconhellmann.github_user.feature.list.component.UserCardView
import com.maiconhellmann.github_user.shared.repository.UserRepository
import com.maiconhellmann.github_user.shared.repository.model.User
import com.maiconhellmann.shared.R
import com.maiconhellmann.shared.components.ErrorStateView
import com.maiconhellmann.shared.viewmodel.ViewState
import kotlinx.coroutines.launch

/**
 * ViewModel responsável por listar os usuários do GitHub.
 *
 * Este ViewModel coordena as ações relacionadas à exibição da lista de usuários do GitHub.
 * Ele utiliza o repositório apropriado para obter os dados necessários.
 *
 * @param userRepository Repositório responsável por lidar com as operações relacionadas aos usuários.
 */
class UserListViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {

    val viewStateLiveData = MutableLiveData<ViewState<List<UserCardView.UiModel>, ErrorStateView.UiModel>>()
    val navigation = MutableLiveData<UiNav>()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            viewStateLiveData.postValue(ViewState.Loading)
            userRepository.fetchUserList().whenSuccess { userList ->
                viewStateLiveData.postValue(ViewState.Success(transformToUiModel(userList)))
            }.whenError { message ->
                viewStateLiveData.postValue(
                    ViewState.Error(
                        ErrorStateView.UiModel(
                            icon = R.drawable.ic_baseline_sentiment_very_dissatisfied_24,
                            text = message,
                        )
                    )
                )
            }
        }
    }

    fun onErrorViewClick() {
        loadData()
    }

    fun onUserClicked(login: String) {
        navigation.postValue(UiNav.NavigateToUserDetails(login))
    }

    fun onSearchUser(newText: String?) {
        val newUserList = when {
            newText.isNullOrBlank() -> userRepository.cachedUserList
            else -> {
                userRepository.cachedUserList.filter { it.login.contains(newText) }
            }
        }
        viewStateLiveData.postValue(ViewState.Success(transformToUiModel(newUserList)))
    }

    sealed class UiNav {
        data class NavigateToUserDetails(val login: String) : UiNav()
    }

    private fun transformToUiModel(userList: List<User>): List<UserCardView.UiModel> {
        return userList.map { user ->
            UserCardView.UiModel(
                user.id,
                user.avatarURL,
                user.login,
            )
        }
    }
}