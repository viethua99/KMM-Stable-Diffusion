package com.vproject.brushai.core.data.repository.style

import com.vproject.brushai.core.model.data.Style
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OfflineFirstStyleRepository @Inject constructor() : StyleRepository {
    private val testList = mutableListOf(
        Style(
            "1",
            "Novelistic",
            "https://cdn.stablediffusionapi.com/generations/29ce2157-a1eb-4356-81c6-e9a57002d5a4-0.png"
        ),
        Style(
            "2",
            "Pen&Ink",
            "https://cdn.stablediffusionapi.com/generations/b2229796-50de-4aec-ba2f-82a60635bc73-0.png"
        ),
        Style(
            "3",
            "Mythological",
            "https://cdn.stablediffusionapi.com/generations/781ad8c1-85bf-4ed7-99fa-bcbb6eb318ae-0.png"
        ),
        Style(
            "4",
            "Magic",
            "https://cdn.stablediffusionapi.com/generations/ee8b516d-74db-4e19-af8a-0c8d54a399a5-0.png"
        ),
        Style(
            "5",
            "Mystical",
            "https://cdn.stablediffusionapi.com/generations/e120b52f-f8ef-4b00-8545-fe461903e935-0.png"
        ),
        Style(
            "6",
            "Oil Painting",
            "https://cdn.stablediffusionapi.com/generations/f3e8ec1a-fa04-49f9-be00-d8cf477a3e99-0.png"
        ),
        Style(
            "7",
            "Authentic",
            "https://cdn.stablediffusionapi.com/generations/39ced130-9d78-46be-8fa7-c29c78a16fed-0.png"
        ),
        Style(
            "8",
            "Watercolor Paint",
            "https://cdn.stablediffusionapi.com/generations/de5977b7-3410-4199-8d96-00c82bd78f1b-0.png"
        ),
        Style(
            "9",
            "Studio Lightning",
            "https://cdn.stablediffusionapi.com/generations/048349f5-48e3-47b1-ac24-72f88a5f6277-0.png"
        ),
        Style(
            "10",
            "Polaroid",
            "https://cdn.stablediffusionapi.com/generations/58c227f3-7144-4a25-972b-087618f2c93c-0.png"
        ),
        Style(
            "11",
            "3D Render",
            "https://cdn.stablediffusionapi.com/generations/6e74a0b0-77d4-4f0e-b6ba-2754c741f955-0.png"
        ),
        Style(
            "12",
            "Fictional Character",
            "https://cdn.stablediffusionapi.com/generations/87e8bcf4-435d-4415-8a80-0b68e85da425-0.png"
        ),
        Style(
            "13",
            "Animation",
            "https://cdn.stablediffusionapi.com/generations/dea02ced-e94a-4f7e-b7cf-cfa0ed394796-0.png"
        ),
        Style(
            "14",
            "Van Gogh",
            "https://pub-8b49af329fae499aa563997f5d4068a4.r2.dev/generations/95c0aac6-cfd5-467a-a3a3-d65808462f49-0.png"
        ),
        Style(
            "15",
            "Digital Art",
            "https://cdn.stablediffusionapi.com/generations/c625ce16-270b-492e-b645-3f8ba829b0ca-0.png"
        ),
        Style(
            "16",
            "Anime Man",
            "https://cdn.stablediffusionapi.com/generations/a8fcf169-b467-41e4-924b-6c168ed73a71-0.png"
        ),
        Style(
            "17",
            "No Style",
            "https://cdn.stablediffusionapi.com/generations/29ce2157-a1eb-4356-81c6-e9a57002d5a4-0.png"
        ),
    )

    override fun getStyles(): Flow<List<Style>> {
        return flow {
            emit(testList)
        }
    }
}