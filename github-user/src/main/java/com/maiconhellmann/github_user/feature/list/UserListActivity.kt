package com.maiconhellmann.github_user.feature.list

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.isVisible
import com.maiconhellmann.github_user.R
import com.maiconhellmann.github_user.databinding.ActivityUserListBinding
import com.maiconhellmann.github_user.feature.detail.UserDetailActivity
import com.maiconhellmann.github_user.feature.list.component.UserCardView
import com.maiconhellmann.shared.components.ErrorStateView
import com.maiconhellmann.shared.viewmodel.ViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserListActivity : AppCompatActivity() {

    private val viewModel: UserListViewModel by viewModel()

    private lateinit var binding: ActivityUserListBinding
    private val adapter = UserListAdapter(::onClickUser)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = getString(R.string.title_user_list)
        binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = adapter

        viewModel.viewStateLiveData.observe(this) { handleState(it) }
        viewModel.navigation.observe(this) { it?.let { handleNavigation(it) } }
    }

    private fun handleState(viewState: ViewState<List<UserCardView.UiModel>, ErrorStateView.UiModel>) {
        setLoading(viewState.isLoading())
        setErrorViewVisibility(viewState.isError())

        when (viewState) {
            is ViewState.Error -> {
                showErrorMessage(viewState.error)
            }
            is ViewState.Success -> {
                adapter.submitList(viewState.state)
            }
            ViewState.Loading -> {} // NA
        }
    }

    private fun handleNavigation(navigation: UserListViewModel.UiNav) {
        when (navigation) {
            is UserListViewModel.UiNav.NavigateToUserDetails -> {
                startActivity(Intent(this, UserDetailActivity::class.java).also { it.putExtras(UserDetailActivity.newBundle(navigation.login)) })
            }
        }
    }

    private fun onClickUser(login: String) {
        viewModel.onUserClicked(login)
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_user_list, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView: SearchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.onSearchUser(newText)
                return true
            }
        })
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId: Int = item.itemId
        return if (itemId == R.id.action_search) {
            true
        } else super.onOptionsItemSelected(item)
    }
}