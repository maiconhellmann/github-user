package com.maiconhellmann.github_user.shared

import androidx.lifecycle.Observer
import io.mockk.spyk

object MockObserverUtils {
    @Suppress("ObjectLiteralToLambda")
    fun <T> createMockObserver(): Observer<T> {
        val observer = object :
            Observer<T> {
            override fun onChanged(t: T?) {}
        }
        return spyk(observer)
    }
}