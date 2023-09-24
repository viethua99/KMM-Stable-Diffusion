package com.vproject.texttoimage.feature.generate

import com.vproject.texttoimage.core.domain.GenerateImageUseCase
import com.vproject.texttoimage.core.domain.GetFavorableStyleListUseCase
import com.vproject.texttoimage.core.domain.ToggleFavoriteStyleUseCase
import com.vproject.texttoimage.core.model.data.FavorableStyle
import com.vproject.texttoimage.core.model.data.Style
import com.vproject.texttoimage.core.testing.repository.TestImageRepository
import com.vproject.texttoimage.core.testing.repository.TestStyleRepository
import com.vproject.texttoimage.core.testing.repository.TestUserDataRepository
import com.vproject.texttoimage.core.testing.util.MainDispatcherRule
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.advanceUntilIdle
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class GenerateViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val styleRepository = TestStyleRepository()
    private val userDataRepository = TestUserDataRepository()
    private val imageRepository = TestImageRepository()

    private val getFavorableStyleListUseCase =
        GetFavorableStyleListUseCase(
            userDataRepository = userDataRepository,
            styleRepository = styleRepository
        )
    private val toggleFavoriteStyleUseCase =
        ToggleFavoriteStyleUseCase(userDataRepository = userDataRepository)
    private val generateImageUseCase =
        GenerateImageUseCase(styleRepository = styleRepository, imageRepository = imageRepository)

    private lateinit var SUT: GenerateViewModel

    @Before
    fun setUp() {
        SUT = GenerateViewModel(
            getFavorableStyleListUseCase = getFavorableStyleListUseCase,
            toggleFavoriteStyleUseCase = toggleFavoriteStyleUseCase,
            generateImageUseCase = generateImageUseCase
        )
    }

    @Test
    fun whenScreenInitialized_thenStateIsLoading() = runTest {
        assertEquals(GenerateUiState.Loading, SUT.generateUiState.value)
    }

    @Test
    fun whenStyleListIsLoaded_thenStateIsStyleSelectionListShown() = runTest {
        val collectJob =
            launch(mainDispatcherRule.testDispatcher) { SUT.generateUiState.collect() }

        styleRepository.sendStyles(sampleStyles)
        userDataRepository.setFavoriteStyleIds(emptySet())


        assertEquals(
            GenerateUiState.Success(
                styles = sampleStyles.map { FavorableStyle(it, false) }
            ), SUT.generateUiState.value
        )

        collectJob.cancel()
    }

    @Test
    fun whenUnselectingFavoriteStyle_thenUpdateFavoriteStyles() = runTest {
        val collectJob =
            launch(mainDispatcherRule.testDispatcher) { SUT.generateUiState.collect() }

        styleRepository.sendStyles(sampleStyles)
        userDataRepository.setFavoriteStyleIds(emptySet())

        // Confirm that all the items are unselected at beginning
        assertEquals(
            GenerateUiState.Success(
                styles = sampleStyles.map { FavorableStyle(it, false) }
            ), SUT.generateUiState.value
        )

        val targetStyleId = sampleStyles[3].id

        SUT.updateFavoriteStyle(targetStyleId, isFavorite = true)
        SUT.updateFavoriteStyle(targetStyleId, isFavorite = false)

        advanceUntilIdle()

        // Confirm that the target favorite id is unchecked
        assertEquals(
            GenerateUiState.Success(
                styles = sampleStyles.map { FavorableStyle(it, false) }
            ), SUT.generateUiState.value
        )

        collectJob.cancel()
    }

    @Test
    fun whenSelectingFavoriteStyle_thenUpdateFavoriteStyles() = runTest {
        val collectJob =
            launch(mainDispatcherRule.testDispatcher) { SUT.generateUiState.collect() }

        styleRepository.sendStyles(sampleStyles)
        userDataRepository.setFavoriteStyleIds(emptySet())

        // Confirm that all the items are un-favorite at beginning
        assertEquals(
            GenerateUiState.Success(
                styles = sampleStyles.map { FavorableStyle(it, false) }
            ), SUT.generateUiState.value
        )

        val favoriteStyleId = sampleStyles[3].id

        SUT.updateFavoriteStyle(favoriteStyleId, true)

        // Confirm that the target favorite id is checked
        assertEquals(
            GenerateUiState.Success(
                styles = sampleStyles.map {
                    FavorableStyle(it, it.id == favoriteStyleId)
                },
            ),
            SUT.generateUiState.value,
        )

        collectJob.cancel()
    }

    @Test
    fun whenGeneratingImageSucceeded_thenReturnValidImageUrl() = runTest {
        val collectJob =
            launch(mainDispatcherRule.testDispatcher) { SUT.generateUiState.collect() }
        val samplePrompt = "Mario is driving a scooter"
        val validUrl = imageRepository.generateImage(samplePrompt)

        collectJob.cancel()
    }
}

private val sampleStyles = listOf(
    Style(
        id = "0",
        name = "Headlines",
        imageUrl = "image URL",
        fullDescription = ""
    ),
    Style(
        id = "1",
        name = "UI",
        imageUrl = "image URL",
        fullDescription = ""
    ),
    Style(
        id = "2",
        name = "Tools",
        imageUrl = "image URL",
        fullDescription = ""
    ),
    Style(
        id = "3",
        name = "Tools",
        imageUrl = "image URL",
        fullDescription = ""
    ),
    Style(
        id = "4",
        name = "Tools",
        imageUrl = "image URL",
        fullDescription = ""
    ),
    Style(
        id = "5",
        name = "Tools",
        imageUrl = "image URL",
        fullDescription = ""
    ),
)