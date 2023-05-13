package com.maiconhellmann.shared.wrappers

import android.content.Context
import androidx.annotation.StringRes

/**
 * Uma classe wrapper para fornecer literais (de strings.xml) a qualquer classe sem usar diretamente o SDK do Android.
 * Esta abordagem é necessária para testes unitários a fim de evitar a simulação ou substituição de classes do Android.
 */
interface StringProvider {
    fun getString(@StringRes resId: Int): String
    fun getString(@StringRes resId: Int, vararg formatArgs: Any?): String
}

class StringProviderImpl(
    private val context: Context,
) : StringProvider {

    override fun getString(@StringRes resId: Int): String {
        return context.getString(resId)
    }

    override fun getString(@StringRes resId: Int, vararg formatArgs: Any?): String {
        return context.getString(resId, *formatArgs)
    }
}