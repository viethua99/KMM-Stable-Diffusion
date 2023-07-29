package com.vproject.brushai.core.data.repository

import com.vproject.brushai.core.data.repository.userdata.OfflineFirstUserDataRepository
import com.vproject.brushai.core.datastore.BrushAiPreferencesDataSource
import com.vproject.brushai.core.datastore.test.testUserPreferencesDataStore
import com.vproject.brushai.core.model.data.DarkThemeConfig
import com.vproject.brushai.core.model.data.UserData
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import kotlin.test.assertEquals

class OfflineFirstUserDataRepositoryTest {
    private val testScope = TestScope(UnconfinedTestDispatcher())

    private lateinit var brushAiPreferencesDataSource: BrushAiPreferencesDataSource

    private lateinit var SUT: OfflineFirstUserDataRepository

    @get:Rule
    val tmpFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()

    @Before
    fun setUp() {
        brushAiPreferencesDataSource = BrushAiPreferencesDataSource(
            tmpFolder.testUserPreferencesDataStore(testScope),
        )
        SUT = OfflineFirstUserDataRepository(
            brushAiPreferencesDataSource = brushAiPreferencesDataSource
        )
    }

    @Test
    fun whenLoadOfflineFirstUserDataRepository_thenDefaultUserDataIsCorrect() =
        testScope.runTest {
            assertEquals(
                UserData(
                    favoriteStyleIds = emptySet(),
                    promptCfgScaleValue = 0f,
                    promptStepValue = 0f,
                    darkThemeConfig = DarkThemeConfig.DARK,
                ),
                SUT.userData.first(),
            )
        }

    @Test
    fun whenSetDarkThemeConfigToLight_thenDelegatesToBrushAiPreferences() =
        testScope.runTest {
            SUT.setDarkThemeConfig(DarkThemeConfig.LIGHT)

            assertEquals(
                DarkThemeConfig.LIGHT,
                SUT.userData
                    .map { it.darkThemeConfig }
                    .first(),
            )
            assertEquals(
                DarkThemeConfig.LIGHT,
                brushAiPreferencesDataSource
                    .userData
                    .map { it.darkThemeConfig }
                    .first(),
            )
        }

    @Test
    fun whenSetDarkThemeConfigToDark_thenDelegatesToBrushAiPreferences() =
        testScope.runTest {
            SUT.setDarkThemeConfig(DarkThemeConfig.DARK)

            assertEquals(
                DarkThemeConfig.DARK,
                SUT.userData
                    .map { it.darkThemeConfig }
                    .first(),
            )
            assertEquals(
                DarkThemeConfig.DARK,
                brushAiPreferencesDataSource
                    .userData
                    .map { it.darkThemeConfig }
                    .first(),
            )
        }

    @Test
    fun whenSetPromptCfgScaleValue_thenDelegatesToBrushAiPreferences() =
        testScope.runTest {
            SUT.setPromptCfgScaleValue(20f)

            assertEquals(
                20f,
                SUT.userData
                    .map { it.promptCfgScaleValue }
                    .first(),
            )
            assertEquals(
                20f,
                brushAiPreferencesDataSource
                    .userData
                    .map { it.promptCfgScaleValue }
                    .first(),
            )
        }

    @Test
    fun whenSetPromptStepValue_thenDelegatesToBrushAiPreferences() =
        testScope.runTest {
            SUT.setPromptStepValue(5f)

            assertEquals(
                5f,
                SUT.userData
                    .map { it.promptStepValue }
                    .first(),
            )
            assertEquals(
                5f,
                brushAiPreferencesDataSource
                    .userData
                    .map { it.promptStepValue }
                    .first(),
            )
        }

    @Test
    fun whenToggleFavoriteStyleId_thenDelegatesToBrushAiPreferences() =
        testScope.runTest {
            SUT.toggleFavoriteStyleId(styleId = "0", isFavorite = true)

            assertEquals(
                setOf("0"),
                SUT.userData
                    .map { it.favoriteStyleIds }
                    .first(),
            )

            SUT.toggleFavoriteStyleId(styleId = "1", isFavorite = true)

            assertEquals(
                setOf("0", "1"),
                SUT.userData
                    .map { it.favoriteStyleIds }
                    .first(),
            )

            assertEquals(
                brushAiPreferencesDataSource.userData
                    .map { it.favoriteStyleIds }
                    .first(),
                SUT.userData
                    .map { it.favoriteStyleIds }
                    .first(),
            )
        }
}