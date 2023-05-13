package com.maiconhellmann.github_user.feature.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.maiconhellmann.github_user.databinding.ItemUserListBinding
import com.maiconhellmann.github_user.feature.list.component.UserCardView

class UserListAdapter(
    private val onItemClick: (login: String) -> Unit
) : ListAdapter<UserCardView.UiModel, UserListAdapter.RepositoryViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(
            ItemUserListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class RepositoryViewHolder(
        private val viewBinding: ItemUserListBinding,
    ) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(uiModel: UserCardView.UiModel) {
            viewBinding.item.loadModel(uiModel)
            viewBinding.root.setOnClickListener { onItemClick(uiModel.login) }
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<UserCardView.UiModel>() {
            override fun areItemsTheSame(oldItem: UserCardView.UiModel, newItem: UserCardView.UiModel): Boolean {
                return oldItem.login == newItem.login
            }

            override fun areContentsTheSame(oldItem: UserCardView.UiModel, newItem: UserCardView.UiModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}