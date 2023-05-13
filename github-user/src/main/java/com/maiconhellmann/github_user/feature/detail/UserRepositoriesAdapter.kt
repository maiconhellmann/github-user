package com.maiconhellmann.github_user.feature.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.maiconhellmann.github_user.databinding.ItemRepoBinding
import com.maiconhellmann.github_user.feature.detail.component.UserRepositoryCardView

class UserRepositoriesAdapter(
    private val onItemClick: (repositoryId: Long) -> Unit
) :
    ListAdapter<UserRepositoryCardView.UiModel, UserRepositoriesAdapter.RepositoryViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(
            ItemRepoBinding.inflate(
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
        private val viewBinding: ItemRepoBinding,
    ) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(uiModel: UserRepositoryCardView.UiModel) {
            viewBinding.item.loadModel(uiModel)
            viewBinding.item.setOnClickListener { onItemClick(uiModel.id) }
        }
    }

    companion object {
        val diffCallback = object : ItemCallback<UserRepositoryCardView.UiModel>() {
            override fun areItemsTheSame(oldItem: UserRepositoryCardView.UiModel, newItem: UserRepositoryCardView.UiModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: UserRepositoryCardView.UiModel, newItem: UserRepositoryCardView.UiModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}

