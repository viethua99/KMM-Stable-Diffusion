package com.vproject.texttoimage

import androidx.annotation.StringRes
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.Espresso
import androidx.test.espresso.NoActivityResumedException
import com.vproject.texttoimage.main.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.properties.ReadOnlyProperty
import com.vproject.texttoimage.feature.generate.R as generateR
import com.vproject.texttoimage.feature.gallery.R as galleryR

@HiltAndroidTest
class NavigationTest {
    /**
     * Manages the components' state and is used to perform injection on your test
     */
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    /**
     * Use the primary activity to initialize the app normally.
     */
    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    private fun AndroidComposeTestRule<*, *>.stringResource(@StringRes resId: Int) =
        ReadOnlyProperty<Any?, String> { _, _ -> activity.getString(resId) }

    // The strings used for matching in these tests
    private val appName by composeTestRule.stringResource(R.string.app_name)
    private val generate by composeTestRule.stringResource(generateR.string.generate)
    private val gallery by composeTestRule.stringResource(galleryR.string.gallery)

    @Test
    fun whenAppStarted_thenFirstScreenIsGenerate() {
        composeTestRule.apply {
            onNodeWithText(generate).assertIsSelected()
        }
    }

    @Test
    fun whenAppStarted_thenShowSettingsIcon() {
        composeTestRule.apply {
            onNodeWithContentDescription("settings").assertExists()

            onNodeWithText(gallery).performClick()
            onNodeWithContentDescription("settings").assertExists()
        }
    }

    @Test(expected = NoActivityResumedException::class)
    fun givenAppStarted_whenPressingBackButton_thenQuittingApp() {
        composeTestRule.apply {
            onNodeWithText(gallery).performClick()
            onNodeWithText(generate).performClick()
            Espresso.pressBack()
        }
    }

    @Test
    fun whenSelectingGalleryTab_thenShowGalleryNavigationTab() {
        composeTestRule.apply {
            // Verify that the top bar contains the app name on the first screen.
            onNodeWithText(appName).assertExists()

            // Go to the gallery tab, verify that the top bar contains "Gallery". This means
            // we'll have 2 elements with the text "Gallery" on screen. One in the top bar, and
            // one in the bottom navigation.
            onNodeWithText(gallery).performClick()
            onAllNodesWithText(gallery).assertCountEquals(2)
        }
    }
}