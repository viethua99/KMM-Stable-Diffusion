package com.vproject.texttoimage.core.data.repository.style

import com.vproject.texttoimage.core.model.data.Style
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class OfflineFirstStyleRepository @Inject constructor() : StyleRepository {
    private val styleList = mutableListOf(
        Style(
            "1",
            "No Style",
            "https://us.123rf.com/450wm/oksanaoo/oksanaoo1710/oksanaoo171000062/88555541-vector-icon-prohibiting-sign-impossible-stop-and-ban-sign-vector-black-icon-on-white-background.jpg?ver=6",
            "",
            ""
        ),
        Style(
            "2",
            "Anime",
            "https://cdn2.stablediffusionapi.com/generations/535d2051-e532-46ca-ad28-202c3431b14f-0.png",
            "anime artwork, anime style, key visual, vibrant, studio anime, highly detailed",
            "photo, deformed, black and white, realism, disfigured, low contrast"
        ),
        Style(
            "3",
            "Photography",
            "https://cdn2.stablediffusionapi.com/generations/10a983ac-ea22-4e58-bce7-388fa211cf62-0.png",
            "cinematic photo . 35mm photograph, film, bokeh, professional, 4k, highly detailed",
            "drawing, painting, crayon, sketch, graphite, impressionist, noisy, blurry, soft, deformed, ugly"
        ),
        Style(
            "4",
            "NSFW",
            "https://cdn.stablediffusionapi.com/generations/de5977b7-3410-4199-8d96-00c82bd78f1b-0.png",
            "nudity content, nsfw, boobs, sex, body, thick",
            ""
        ),
        Style(
            "5",
            "Fantasy Art",
            "https://cdn2.stablediffusionapi.com/generations/2464ede4-4538-438e-b364-9fcc858a43d4-0.png",
            "ethereal fantasy concept art of. magnificent, celestial, ethereal, painterly, epic, majestic, magical, fantasy art, cover art, dreamy",
            "photographic, realistic, realism, 35mm film, dslr, cropped, frame, text, deformed, glitch, noise, noisy, off-center, deformed, cross-eyed, closed eyes, bad anatomy, ugly, disfigured, sloppy, duplicate, mutated, black and white"
        ),
        Style(
            "6",
            "Concept Art",
            "https://cdn2.stablediffusionapi.com/generations/b1478590-9382-42f6-b7e4-484669020d3d-0.png",
            "concept art. digital artwork, illustrative, painterly, matte painting, highly detailed",
            "photo, photorealistic, realism, ugly"
        ),
        Style(
            "7",
            "Isometric",
            "https://cdn2.stablediffusionapi.com/generations/a93655ef-a71f-4777-8471-195b78bf2330-0.png",
            "isometric style . vibrant, beautiful, crisp, detailed, ultra detailed, intricate",
            "deformed, mutated, ugly, disfigured, blur, blurry, noise, noisy, realistic, photographic"
        ),
        Style(
            "8",
            "Cyberpunk",
            "https://cdn2.stablediffusionapi.com/generations/06324b56-9d94-40af-9ee0-04abbd86e4ba-0.png",
            "vaporwave synthwave style . cyberpunk, neon, vibes, stunningly beautiful, crisp, detailed, sleek, ultramodern, high contrast, cinematic composition",
            "illustration, painting, crayon, graphite, abstract, glitch, deformed, mutated, ugly, disfigured"
        ),
        Style(
            "9",
            "Claymation",
            "https://cdn2.stablediffusionapi.com/generations/29446c1a-99c1-4460-8e0d-beb101daf20e-0.png",
            "claymation style. sculpture, clay art, centered composition, play-doh",
            "sloppy, messy, grainy, highly detailed, ultra textured, photo, mutated"
        ),
        Style(
            "10",
            "Low Poly",
            "https://cdn2.stablediffusionapi.com/generations/0592398c-2299-45bb-a5ad-dbce62fa8547-0.png",
            "clow-poly style. ambient occlusion, low-poly game art, polygon mesh, jagged, blocky, wireframe edges, centered composition",
            "noisy, sloppy, messy, grainy, highly detailed, ultra textured, photo"
        ),
    )

    override fun getStyles(): Flow<List<Style>> = flow { emit(styleList) }

    override fun getStyle(id: String): Flow<Style> = flow { emit(styleList.first { it.id == id }) }
}