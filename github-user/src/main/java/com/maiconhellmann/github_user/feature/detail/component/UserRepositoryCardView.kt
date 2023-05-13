package com.maiconhellmann.github_user.feature.detail.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import com.maiconhellmann.github_user.databinding.ViewRepositoryCardBinding

/**
 * Componente que exibe informações de um repositório do usuário.
 *
 * Este componente exibe informações sobre um repositório do usuário, como o nome do repositório,
 * a descrição, a quantidade de estrelas, a quantidade de forks e o número de issues abertas.
 */
class UserRepositoryCardView : CardView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val binding = ViewRepositoryCardBinding.inflate(
        LayoutInflater.from(context),
        this,
    )

    fun loadModel(uiModel: UiModel) {
        binding.repoNameTextView.text = uiModel.name
        binding.repoDescriptionTextView.text = uiModel.description
        binding.starsTextView.text = uiModel.stars
        binding.forksTextView.text = uiModel.forks
        binding.openIssuesTextView.text = uiModel.openIssues
    }

    data class UiModel(
        val id: Long,
        val name: String,
        val description: String,
        val stars: String,
        val forks: String,
        val openIssues: String,
    )
}