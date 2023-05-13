package com.maiconhellmann.github_user.feature.list

import androidx.lifecycle.Observer
import com.maiconhellmann.github_user.feature.list.component.UserCardView
import com.maiconhellmann.github_user.shared.MockKTest
import com.maiconhellmann.github_user.shared.MockObserverUtils
import com.maiconhellmann.github_user.shared.repository.UserRepository
import com.maiconhellmann.github_user.shared.repository.model.User
import com.maiconhellmann.shared.R
import com.maiconhellmann.shared.components.ErrorStateView
import com.maiconhellmann.shared.networking.repository.RepositoryResult
import com.maiconhellmann.shared.viewmodel.ViewState
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@Suppress("ObjectLiteralToLambda")
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class UserListViewModelTest : MockKTest() {

    private lateinit var viewStateObserver: Observer<ViewState<List<UserCardView.UiModel>, ErrorStateView.UiModel>>

    @MockK
    lateinit var repository: UserRepository

    @After
    fun tearDown() {
        confirmVerified(repository, viewStateObserver)
    }

    @Test
    fun initSuccess() {
        // Given
        coEvery { repository.fetchUserList() } returns RepositoryResult.Success(USER_LIST)

        // When
        createViewModel()


        // Then
        coVerify {
            repository.fetchUserList()
            viewStateObserver.onChanged(
                ViewState.Success(
                    listOf(
                        UserCardView.UiModel(
                            id = ID,
                            avatarUrl = AVATAR_URL,
                            login = LOGIN
                        )
                    )
                )
            )
        }
    }

    @Test
    fun initError() {
        // Given
        coEvery { repository.fetchUserList() } returns RepositoryResult.Error(400, "Error")

        // When
        createViewModel()

        // Then
        coVerify {
            repository.fetchUserList()
            viewStateObserver.onChanged(
                ViewState.Error(
                    ErrorStateView.UiModel(
                        icon = R.drawable.ic_baseline_sentiment_very_dissatisfied_24,
                        text = "Error"
                    )
                )
            )
        }
    }

    @Test
    fun onError() {
        // Given
        val viewModel = createViewModelAndDisregardInit()
        coEvery { repository.fetchUserList() } returns RepositoryResult.Success(USER_LIST)

        // When
        viewModel.onErrorViewClick()

        // Then
        coVerify {
            repository.fetchUserList()
            viewStateObserver.onChanged(ViewState.Loading)
            viewStateObserver.onChanged(
                ViewState.Success(
                    state = listOf(
                        UserCardView.UiModel(
                            id = ID,
                            avatarUrl = AVATAR_URL,
                            login = LOGIN
                        )
                    )
                )
            )
        }

    }

    private fun createViewModelAndDisregardInit(): UserListViewModel {
        val viewModel = createViewModel()

        clearAllMocks()

        return viewModel
    }

    private fun createViewModel(): UserListViewModel {
        val viewModel = UserListViewModel(repository)

        viewStateObserver = MockObserverUtils.createMockObserver()
        viewModel.viewStateLiveData.observeForever(viewStateObserver)

        return viewModel
    }

    companion object {
        private const val LOGIN = "maiconhellmann"
        private const val AVATAR_URL = "https://example.com/avatar/maiconhellmann"
        private const val ID = 1L

        private val USER_LIST = listOf(
            User(
                login = LOGIN,
                id = ID,
                nodeID = "nodeId",
                avatarURL = AVATAR_URL,
                gravatarID = "",
                url = "",
                htmlURL = "",
                followersURL = "",
                followingURL = "",
                gistsURL = "",
                starredURL = "",
                subscriptionsURL = "",
                organizationsURL = "",
                reposURL = "",
                eventsURL = "",
                receivedEventsURL = "",
                type = "",
                siteAdmin = false,
            )
        )
    }
}