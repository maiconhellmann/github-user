package com.maiconhellmann.github_user.feature.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.maiconhellmann.github_user.databinding.ActivityUserDetailBinding
import com.maiconhellmann.github_user.feature.detail.component.UserRepositoryCardView
import com.maiconhellmann.github_user.shared.repository.model.UserDetail
import com.maiconhellmann.shared.components.ErrorStateView
import com.maiconhellmann.shared.viewmodel.ViewState
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class UserDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailBinding

    private val adapter = UserRepositoriesAdapter(::onClickRepo)

    private val viewModel: UserDetailViewModel by viewModel {
        parametersOf(intent.getStringExtra(KEY_LOGIN))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = getString(com.maiconhellmann.github_user.R.string.title_user_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = adapter

        viewModel.userDetailViewState.observe(this) { handleUserDetailState(it) }
        viewModel.repositoriesViewState.observe(this) { handleRepositoriesState(it) }
        viewModel.navigation.observe(this) { handleNavigation(it) }
    }

    private fun handleNavigation(uiNav: UserDetailViewModel.UiNav) {
        when (uiNav) {
            UserDetailViewModel.UiNav.NavigateToUserList -> finish()
        }
    }

    private fun handleRepositoriesState(viewState: ViewState<List<UserRepositoryCardView.UiModel>, ErrorStateView.UiModel>) {
        setRepositoriesLoading(viewState.isLoading())
        setRepositoriesErrorViewVisibility(viewState.isError())

        when (viewState) {
            is ViewState.Error -> {
                showErrorMessage(viewState.error)
            }
            is ViewState.Success -> {
                adapter.submitList(viewState.state)
            }
            ViewState.Loading -> {
                // N / A
            }
        }
    }

    private fun handleUserDetailState(viewState: ViewState<UserDetail, ErrorStateView.UiModel>) {
        setUserLoading(viewState.isLoading())
        setUserErrorViewVisibility(viewState.isError())

        when (viewState) {
            is ViewState.Error -> {
                showErrorMessage(viewState.error)
            }
            is ViewState.Success -> {
                with(binding) {
                    login.text = viewState.state.login
                    name.text = viewState.state.name
                    Glide.with(avatarImageView)
                        .load(viewState.state.avatarURL)
                        .into(avatarImageView)
                }
            }
            ViewState.Loading -> {} // NA
        }
    }

    private fun showErrorMessage(error: ErrorStateView.UiModel) {
        binding.avatarImageView.isVisible = false
        binding.login.isVisible = false
        binding.name.isVisible = false
        binding.recyclerView.isVisible = false
        binding.sectionRepositories.isVisible = false

        with(binding.userErrorStateViw) {
            isVisible = true
            loadModel(error)
            setOnClickListener { viewModel.onErrorViewClick() }
        }
    }

    private fun setUserErrorViewVisibility(isVisible: Boolean) {
        binding.userErrorStateViw.isVisible = isVisible
    }

    private fun setUserLoading(isLoading: Boolean) {
        binding.shimmerUserViewContainer.isVisible = isLoading

        binding.avatarImageView.isInvisible = isLoading
        binding.login.isInvisible = isLoading
        binding.name.isInvisible = isLoading
    }

    private fun setRepositoriesErrorViewVisibility(isVisible: Boolean) {
        binding.userErrorStateViw.isVisible = isVisible
    }

    private fun setRepositoriesLoading(isLoading: Boolean) {
        binding.recyclerView.isVisible = !isLoading
        binding.shimmerRepoViewContainer.isVisible = isLoading
    }

    private fun onClickRepo(id: Long) {
        // TODO
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            viewModel.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val KEY_LOGIN = "key:login"

        fun newBundle(login: String): Bundle {
            return bundleOf(
                KEY_LOGIN to login,
            )
        }
    }
}