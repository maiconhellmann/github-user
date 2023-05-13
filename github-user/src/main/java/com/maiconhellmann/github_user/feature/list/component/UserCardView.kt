package com.maiconhellmann.github_user.feature.list.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.maiconhellmann.github_user.databinding.ViewUserCardBinding

class UserCardView : CardView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val binding = ViewUserCardBinding.inflate(
        LayoutInflater.from(context),
        this,
    )

    fun loadModel(uiModel: UiModel) {
        binding.loginTextView.text = uiModel.login
        Glide.with(this)
            .load(uiModel.avatarUrl)
            .into(binding.imageViewAvatar)
    }

    data class UiModel(
        val id: Long,
        val avatarUrl: String,
        val login: String,
    )
}