package com.maiconhellmann.shared.wrappers

import android.content.Context
import androidx.annotation.StringRes

/**
 * A wrapper class to provide Literals(from strings.xml) to any class without directly using Android SDK.
 * This approach is required for unit testes to avoid mocking/stubbing Android classes.
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