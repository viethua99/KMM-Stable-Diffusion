package com.vproject.texttoimage.core.data.repository

import com.vproject.texttoimage.core.data.repository.userdata.OfflineFirstUserDataRepository
import com.vproject.texttoimage.core.datastore.TextToImagePreferencesDataSource
import com.vproject.texttoimage.core.datastore.test.testUserPreferencesDataStore
import com.vproject.texttoimage.core.model.data.DarkThemeConfig
import com.vproject.texttoimage.core.model.data.UserData
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
//    private val testScope = TestScope(UnconfinedTestDispatcher())
//
//    private lateinit var textToImagePreferencesDataSource: TextToImagePreferencesDataSource
//
//    private lateinit var SUT: OfflineFirstUserDataRepository
//
//    @get:Rule
//    val tmpFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()
//
//    @Before
//    fun setUp() {
//        textToImagePreferencesDataSource = TextToImagePreferencesDataSource(
//            tmpFolder.testUserPreferencesDataStore(testScope),
//        )
//        SUT = OfflineFirstUserDataRepository(
//            textToImagePreferencesDataSource = textToImagePreferencesDataSource
//        )
//    }
//
//    @Test
//    fun whenLoadOfflineFirstUserDataRepository_thenDefaultUserDataIsCorrect() =
//        testScope.runTest {
//            assertEquals(
//                UserData(
//                    favoriteStyleIds = emptySet(),
//                    promptCfgScaleValue = 0f,
//                    promptStepValue = 0f,
//                    darkThemeConfig = DarkThemeConfig.DARK,
//                ),
//                SUT.userData.first(),
//            )
//        }
//
//    @Test
//    fun whenSetDarkThemeConfigToLight_thenDelegatesToTextToImagePreferences() =
//        testScope.runTest {
//            SUT.setDarkThemeConfig(DarkThemeConfig.LIGHT)
//
//            assertEquals(
//                DarkThemeConfig.LIGHT,
//                SUT.userData
//                    .map { it.darkThemeConfig }
//                    .first(),
//            )
//            assertEquals(
//                DarkThemeConfig.LIGHT,
//                textToImagePreferencesDataSource
//                    .userData
//                    .map { it.darkThemeConfig }
//                    .first(),
//            )
//        }
//
//    @Test
//    fun whenSetDarkThemeConfigToDark_thenDelegatesToTextToImagePreferences() =
//        testScope.runTest {
//            SUT.setDarkThemeConfig(DarkThemeConfig.DARK)
//
//            assertEquals(
//                DarkThemeConfig.DARK,
//                SUT.userData
//                    .map { it.darkThemeConfig }
//                    .first(),
//            )
//            assertEquals(
//                DarkThemeConfig.DARK,
//                textToImagePreferencesDataSource
//                    .userData
//                    .map { it.darkThemeConfig }
//                    .first(),
//            )
//        }
//
//    @Test
//    fun whenSetPromptCfgScaleValue_thenDelegatesToTextToImagePreferences() =
//        testScope.runTest {
//            SUT.setPromptCfgScaleValue(20f)
//
//            assertEquals(
//                20f,
//                SUT.userData
//                    .map { it.promptCfgScaleValue }
//                    .first(),
//            )
//            assertEquals(
//                20f,
//                textToImagePreferencesDataSource
//                    .userData
//                    .map { it.promptCfgScaleValue }
//                    .first(),
//            )
//        }
//
//    @Test
//    fun whenSetPromptStepValue_thenDelegatesToTextToImagePreferences() =
//        testScope.runTest {
//            SUT.setPromptStepValue(5f)
//
//            assertEquals(
//                5f,
//                SUT.userData
//                    .map { it.promptStepValue }
//                    .first(),
//            )
//            assertEquals(
//                5f,
//                textToImagePreferencesDataSource
//                    .userData
//                    .map { it.promptStepValue }
//                    .first(),
//            )
//        }
//
//    @Test
//    fun whenToggleFavoriteStyleId_thenDelegatesToTextToImagePreferences() =
//        testScope.runTest {
//            SUT.toggleFavoriteStyleId(styleId = "0", isFavorite = true)
//
//            assertEquals(
//                setOf("0"),
//                SUT.userData
//                    .map { it.favoriteStyleIds }
//                    .first(),
//            )
//
//            SUT.toggleFavoriteStyleId(styleId = "1", isFavorite = true)
//
//            assertEquals(
//                setOf("0", "1"),
//                SUT.userData
//                    .map { it.favoriteStyleIds }
//                    .first(),
//            )
//
//            assertEquals(
//                textToImagePreferencesDataSource.userData
//                    .map { it.favoriteStyleIds }
//                    .first(),
//                SUT.userData
//                    .map { it.favoriteStyleIds }
//                    .first(),
//            )
//        }
}