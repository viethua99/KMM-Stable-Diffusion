package com.vproject.brushai

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.google.accompanist.testharness.TestHarness
import com.vproject.brushai.ui.BrushAiApp
import com.vproject.brushai.uitesthiltmanifest.HiltComponentActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Tests that the navigation UI is rendered correctly on different screen sizes.
 * Screen sizes reference: https://developer.android.com/guide/topics/large-screens/support-different-screen-sizes
 */
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@HiltAndroidTest
class NavigationUiTest {
    /**
     * Manages the components' state and is used to perform injection on your test
     */
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    /**
     * Use a test activity to set the content on.
     */
    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<HiltComponentActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun givenCompactWidthCompactHeight_whenAppStarted_thenShowsNavigationBar() {
        composeTestRule.setContent {
            TestHarness(size = DpSize(400.dp, 400.dp)) {
                BoxWithConstraints {
                    BrushAiApp(
                        windowSizeClass = WindowSizeClass.calculateFromSize(
                            DpSize(maxWidth, maxHeight),
                        )
                    )
                }
            }
        }
        composeTestRule.onNodeWithTag("BrushAiBottomBar").assertIsDisplayed()
    }

    @Test
    fun givenCompactWidthMediumHeight_whenAppStarted_thenShowsNavigationBar() {
        composeTestRule.setContent {
            TestHarness(size = DpSize(400.dp, 500.dp)) {
                BoxWithConstraints {
                    BrushAiApp(
                        windowSizeClass = WindowSizeClass.calculateFromSize(
                            DpSize(maxWidth, maxHeight),
                        )
                    )
                }
            }
        }
        composeTestRule.onNodeWithTag("BrushAiBottomBar").assertIsDisplayed()
    }

    @Test
    fun givenCompactWidthExpandedHeight_whenAppStarted_thenShowsNavigationBar() {
        composeTestRule.setContent {
            TestHarness(size = DpSize(400.dp, 1000.dp)) {
                BoxWithConstraints {
                    BrushAiApp(
                        windowSizeClass = WindowSizeClass.calculateFromSize(
                            DpSize(maxWidth, maxHeight),
                        )
                    )
                }
            }
        }
        composeTestRule.onNodeWithTag("BrushAiBottomBar").assertIsDisplayed()
    }

    @Test
    fun givenMediumWidthCompactHeight_whenAppStarted_thenShowsNavigationBar() {
        composeTestRule.setContent {
            TestHarness(size = DpSize(500.dp, 400.dp)) {
                BoxWithConstraints {
                    BrushAiApp(
                        windowSizeClass = WindowSizeClass.calculateFromSize(
                            DpSize(maxWidth, maxHeight),
                        )
                    )
                }
            }
        }
        composeTestRule.onNodeWithTag("BrushAiBottomBar").assertIsDisplayed()
    }

    @Test
    fun givenMediumWidthMediumHeight_whenAppStarted_thenShowsNavigationBar() {
        composeTestRule.setContent {
            TestHarness(size = DpSize(500.dp, 500.dp)) {
                BoxWithConstraints {
                    BrushAiApp(
                        windowSizeClass = WindowSizeClass.calculateFromSize(
                            DpSize(maxWidth, maxHeight),
                        )
                    )
                }
            }
        }
        composeTestRule.onNodeWithTag("BrushAiBottomBar").assertIsDisplayed()
    }
}