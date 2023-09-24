package com.vproject.brushai.core.data.repository.style

import com.vproject.brushai.core.model.data.Style
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class OfflineFirstStyleRepository @Inject constructor() : StyleRepository {
    private val styleList = mutableListOf(
        Style(
            "1",
            "Animal",
            "https://cdn.stablediffusionapi.com/generations/29ce2157-a1eb-4356-81c6-e9a57002d5a4-0.png",
            "wildlife photography, photograph, high quality, wildlife, f 1.8, soft focus, 8k, national geographic, award - winning photograph by nick nichols"
        ),
        Style(
            "2",
            "Archviz",
            "https://cdn.stablediffusionapi.com/generations/b2229796-50de-4aec-ba2f-82a60635bc73-0.png",
        "by James McDonald and Joarc Architects, home, interior, octane render, deviantart, cinematic, key art, hyperrealism, sun light, sunrays, canon eos c 300, ƒ 1.8, 35 mm, 8k, medium - format print"
        ),
        Style(
            "3",
            "Building",
            "https://cdn.stablediffusionapi.com/generations/781ad8c1-85bf-4ed7-99fa-bcbb6eb318ae-0.png",
            "shot 35 mm, realism, octane render, 8k, trending on artstation, 35 mm camera, unreal engine, hyper detailed, photo - realistic maximum detail, volumetric light, realistic matte painting, hyper photorealistic, trending on artstation, ultra - detailed, realistic"
        ),
        Style(
            "4",
            "Cartoon Character",
            "https://cdn.stablediffusionapi.com/generations/ee8b516d-74db-4e19-af8a-0c8d54a399a5-0.png",
            "anthro, very cute kid's film character, disney pixar zootopia character concept artwork, 3d concept, detailed fur, high detail iconic character for upcoming film, trending on artstation, character design, 3d artistic render, highly detailed, octane, blender, cartoon, shadows, lighting"
        ),
        Style(
            "5",
            "Concept Art",
            "https://cdn.stablediffusionapi.com/generations/e120b52f-f8ef-4b00-8545-fe461903e935-0.png",
            "character sheet, concept design, contrast, style by kim jung gi, zabrocki, karlkka, jayison devadas, trending on artstation, 8k, ultra wide angle, pincushion lens effect"
        ),
        Style(
            "6",
            "Cyberpunk",
            "https://cdn.stablediffusionapi.com/generations/f3e8ec1a-fa04-49f9-be00-d8cf477a3e99-0.png",
            "cyberpunk, in heavy raining futuristic tokyo rooftop cyberpunk night, sci-fi, fantasy, intricate, very very beautiful, elegant, neon light, highly detailed, digital painting, artstation, concept art, soft light, hdri, smooth, sharp focus, illustration, art by tian zi and craig mullins and wlop and alphonse much"
        ),
        Style(
            "7",
            "Digital Art",
            "https://cdn.stablediffusionapi.com/generations/39ced130-9d78-46be-8fa7-c29c78a16fed-0.png",
            "ultra realistic, concept art, intricate details, highly detailed, photorealistic, octane render, 8k, unreal engine, sharp focus, volumetric lighting unreal engine. art by artgerm and alphonse mucha"
        ),
        Style(
            "8",
            "Drawing",
            "https://cdn.stablediffusionapi.com/generations/de5977b7-3410-4199-8d96-00c82bd78f1b-0.png",
            "cute, funny, centered, award winning watercolor pen illustration, detailed, disney, isometric illustration, drawing, by Stephen Hillenburg, Matt Groening, Albert Uderzo"
        ),
        Style(
            "9",
            "Fashion",
            "https://cdn.stablediffusionapi.com/generations/048349f5-48e3-47b1-ac24-72f88a5f6277-0.png",
            "photograph of a Fashion model, full body, highly detailed and intricate, golden ratio, vibrant colors, hyper maximalist, futuristic, city background, luxury, elite, cinematic, fashion, depth of field, colorful, glow, trending on artstation, ultra high detail, ultra realistic, cinematic lighting, focused, 8k,"
        ),
        Style(
            "10",
            "Landscape",
            "https://cdn.stablediffusionapi.com/generations/58c227f3-7144-4a25-972b-087618f2c93c-0.png",
            "birds in the sky, waterfall close shot 35 mm, realism, octane render, 8 k, exploration, cinematic, trending on artstation, 35 mm camera, unreal engine, hyper detailed, photo - realistic maximum detail, volumetric light, moody cinematic epic concept art, realistic matte painting, hyper photorealistic, epic, trending on artstation, movie concept art, cinematic composition, ultra - detailed, realistic"
        ),
        Style(
            "11",
            "Portrait",
            "https://cdn.stablediffusionapi.com/generations/6e74a0b0-77d4-4f0e-b6ba-2754c741f955-0.png",
            "portrait photo, photograph, highly detailed face, depth of field, moody light, golden hour, style by Dan Winters, Russell James, Steve McCurry, centered, extremely detailed, Nikon D850, award winning photography"
        ),
        Style(
            "12",
            "Space",
            "https://cdn.stablediffusionapi.com/generations/87e8bcf4-435d-4415-8a80-0b68e85da425-0.png",
            "by Andrew McCarthy, Navaneeth Unnikrishnan, Manuel Dietrich, photo realistic, 8 k, cinematic lighting, hd, atmospheric, hyperdetailed, trending on artstation, deviantart, photography, glow effect"
        ),
        Style(
            "13",
            "Steampunk",
            "https://cdn.stablediffusionapi.com/generations/dea02ced-e94a-4f7e-b7cf-cfa0ed394796-0.png",
            "steampunk cybernetic biomechanical, 3d model, very coherent symmetrical artwork, unreal engine realistic render, 8k, micro detail, intricate, elegant, highly detailed, centered, digital painting, artstation, smooth, sharp focus, illustration, artgerm, Caio Fantini, wlop"
        ),
        Style(
            "14",
            "Vehicles",
            "https://pub-8b49af329fae499aa563997f5d4068a4.r2.dev/generations/95c0aac6-cfd5-467a-a3a3-d65808462f49-0.png",
            " photorealistic, vivid, sharp focus, reflection, refraction, sunrays, very detailed, intricate, intense cinematic composition"
        ),
        Style(
            "15",
            "No Style",
            "https://cdn.stablediffusionapi.com/generations/29ce2157-a1eb-4356-81c6-e9a57002d5a4-0.png",
            ""
        ),
    )

    override fun getStyles(): Flow<List<Style>> = flow { emit(styleList) }

    override fun getStyle(id: String): Flow<Style> = flow { emit(styleList.first { it.id == id }) }
}