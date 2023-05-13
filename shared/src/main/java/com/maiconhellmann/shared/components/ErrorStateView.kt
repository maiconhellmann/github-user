package com.maiconhellmann.shared.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.maiconhellmann.shared.R
import com.maiconhellmann.shared.databinding.ViewErrorStateBinding

/**
 * Exibe uma imagem e um texto que representam um erro.
 * Pode ser usado como um estado vazio ou como uma opção de tentar novamente.
 */
class ErrorStateView : ConstraintLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private var binding: ViewErrorStateBinding =
        ViewErrorStateBinding.inflate(LayoutInflater.from(context), this)

    fun loadModel(uiModel: UiModel) {
        binding.iconImageView.setImageResource(uiModel.icon)
        binding.errorTextView.text = uiModel.text
    }

    data class UiModel(
        @DrawableRes val icon: Int = R.drawable.ic_baseline_sentiment_very_dissatisfied_24,
        val text: String,
    )
}