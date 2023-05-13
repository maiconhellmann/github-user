package com.maiconhellmann.github_user.shared

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest

@ExperimentalCoroutinesApi
abstract class MockKTest : AutoCloseKoinTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    val mockKTestRule = MockKTestRule(this)

    @Before
    fun setupKoin() {
        startKoin {
        }
    }

    fun runBlockingTest(block : () -> Unit) {
        coroutineTestRule.run {
            block()
        }
    }
}