package com.maiconhellmann.github_user.shared

import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.unmockkAll
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class MockKTestRule(private val obj: Any) : TestWatcher() {
    override fun starting(description: Description) {
        super.starting(description)
        MockKAnnotations.init(obj)
    }

    override fun finished(description: Description) {
        super.finished(description)
        clearAllMocks()
        unmockkAll()
    }
}