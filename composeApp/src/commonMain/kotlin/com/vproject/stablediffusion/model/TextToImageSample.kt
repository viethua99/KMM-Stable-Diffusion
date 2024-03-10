package com.vproject.stablediffusion.model

import com.vproject.stablediffusion.SharedRes
import dev.icerock.moko.resources.ImageResource

data class TextToImageSample(
    val id: Long,
    val prompt: String,
    val stylePreset: StylePreset,
    val canvasPreset: CanvasPreset,
    val imageResource: ImageResource
)

val textToImageSampleList = listOf(
    TextToImageSample(
        0,
        "Dragon New Year poster, highest quality, lanterns, fireworks, lucky red envelope, flying, sky",
        StylePreset.ANIME,
        CanvasPreset.THREE_FOUR,
        SharedRes.images.img_tti_sample1
    ),
    TextToImageSample(
        4,
        "bustling street market,New Year's celebration,vibrant stalls,traditional decorations,delicacies,festive attire,red lantern,colorful banners,streamers",
        StylePreset.LINE_ART,
        CanvasPreset.THREE_FOUR,
        SharedRes.images.img_tti_sample5
    ),
    TextToImageSample(
        2,
        "1 boy, male focus, solo, realistic, shirt, looking at viewer, black hair, blurry, tree, black shirt, blurry background, collared shirt, white shirt, portrait, outdoors, black eyes, upper body, short hair, closed mouth, smile, open clothes, brown eyes",
        StylePreset.COMIC_BOOK,
        CanvasPreset.THREE_FOUR,
        SharedRes.images.img_tti_sample3
    ),
    TextToImageSample(
        1,
        "Masterpiece, high quality, best quality, 4k, ultra detailed, realistic, 1girl, upper body, white swimsuit, (light pink hair), flipped hair, pink eyes, summer, beautiful and detailed face, sun, ray chasing, reflection light",
        StylePreset.MODEL_3D,
        CanvasPreset.THREE_FOUR,
        SharedRes.images.img_tti_sample2
    ),
    TextToImageSample(
        3,
        "masterpiece, best quality, floating city, clouds, sun",
        StylePreset.FANTASY_ART,
        CanvasPreset.THREE_FOUR,
        SharedRes.images.img_tti_sample4
    ),
    TextToImageSample(
        5,
        "English Medieval Witch, <Unique Nose*Blue Eyes*Sitting On A Throne*Elegant*Real Light>, beautiful",
        StylePreset.PHOTOGRAPHIC,
        CanvasPreset.THREE_FOUR,
        SharedRes.images.img_tti_sample6
    ),
    TextToImageSample(
        6,
        "1girl, extremely detailed, 8k wallpaper, rabbit ear, a background of extreme detail, (red eyes (extreme detail))",
        StylePreset.PHOTOGRAPHIC,
        CanvasPreset.THREE_FOUR,
        SharedRes.images.img_tti_sample7
    ),
    TextToImageSample(
        7,
        "(fluorescent colors:1.4), (translucent:1.4), (retro filters:1.4), (fantasy:1.4), candy world disney land ethereal soft fluffy soft landscape forest snowavatar pastel pink sky green blue sparkle ethereal light pastel whimsical light rainbow stars diamonds sparkle gemstone background hyper realistic ultra quality cinematic lighting immense detail full hd painting well lit, diagonal bangs, collared dress, on side masterpiece, best quality, realskin, (portrait:1.5), 1girl, blunt bangs, long hair",
        StylePreset.PHOTOGRAPHIC,
        CanvasPreset.THREE_FOUR,
        SharedRes.images.img_tti_sample8
    ),
    TextToImageSample(
        8,
        "a bear is fishing, cute, river, forest, flowers, tree, cloud, sun",
        StylePreset.ANIME,
        CanvasPreset.THREE_FOUR,
        SharedRes.images.img_tti_sample9
    ),
    TextToImageSample(
        9,
        "Dog, <Masterpiece*Golden Ratio*Sunlight*Spring>",
        StylePreset.LOW_POLY,
        CanvasPreset.THREE_FOUR,
        SharedRes.images.img_tti_sample10
    ),
)