package com.vproject.brushai.feature.loading

import com.vproject.brushai.core.testing.util.MainDispatcherRule
import org.junit.Before
import org.junit.Rule

class LoadingViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var SUT: LoadingViewModel

    @Before
    fun setUp() {
        SUT = LoadingViewModel()
    }
}