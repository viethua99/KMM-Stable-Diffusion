package com.vproject.brushai.feature.settings

import com.vproject.brushai.core.testing.util.MainDispatcherRule
import org.junit.Before
import org.junit.Rule

class SettingsViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var SUT: SettingsViewModel

    @Before
    fun setUp() {
        SUT = SettingsViewModel()
    }
}