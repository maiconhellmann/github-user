package com.maiconhellmann.github_user.feature.detail

import android.R
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.maiconhellmann.github_user.databinding.ActivityUserDetailBinding
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
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = adapter

        viewModel.viewStateLiveData.observe(this) { handleViewState(it) }
        viewModel.navigation.observe(this) { handleNavigation(it) }
    }

    private fun handleNavigation(uiNav: UserDetailViewModel.UiNav) {
        when (uiNav) {
            UserDetailViewModel.UiNav.NavigateToUserList -> finish()
        }
    }

    private fun handleViewState(viewState: ViewState<UserDetailViewModel.State, ErrorStateView.UiModel>) {
        setLoading(viewState.isLoading())
        setErrorViewVisibility(viewState.isError())

        when (viewState) {
            is ViewState.Error -> {
                showErrorMessage(viewState.error)
            }
            is ViewState.Success -> {
                with(binding) {
                    login.text = viewState.state.login
                    name.text = viewState.state.name
                    Glide.with(avatarImageView)
                        .load(viewState.state.avatarUrl)
                        .into(avatarImageView)

                    adapter.submitList(viewState.state.repositoryUiModelList)
                }
            }
            ViewState.Loading -> {} // NA
        }
    }

    private fun showErrorMessage(error: ErrorStateView.UiModel) {
        with(binding.errorStateViw) {
            loadModel(error)
            setOnClickListener { viewModel.onErrorViewClick() }
        }
    }

    private fun setErrorViewVisibility(isVisible: Boolean) {
        binding.errorStateViw.isVisible = isVisible
    }

    private fun setLoading(isLoading: Boolean) {
        binding.loadingView.isVisible = isLoading
    }

    private fun onClickRepo(id: Long) {
        // TODO
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.home) {
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