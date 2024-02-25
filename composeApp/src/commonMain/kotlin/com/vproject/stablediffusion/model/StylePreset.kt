package com.vproject.stablediffusion.model

import com.vproject.stablediffusion.SharedRes
import dev.icerock.moko.resources.ImageResource

enum class StylePreset(val id: String, val displayName: String, val imageResource: ImageResource) {
    ANIME("anime", "Anime", SharedRes.images.img_style_preset_anime),
    MODEL_3D("3d-model", "3D Model", SharedRes.images.img_style_preset_model3d),
    PHOTOGRAPHIC("photographic", "Photographic", SharedRes.images.img_style_preset_photographic),
    COMIC_BOOK("comic-book", "Comic Book", SharedRes.images.img_style_preset_comic_book),
    PIXEL_ART("pixel-art", "Pixel Art", SharedRes.images.img_style_preset_pixel_art),
    NEON_PUNK("neon-punk", "Neon Punk", SharedRes.images.img_style_preset_neon_punk),
    FANTASY_ART("fantasy-art", "Fantasy Art", SharedRes.images.img_style_preset_fantasy_art),
    DIGITAL_ART("digital-art", "Digital Art", SharedRes.images.img_style_preset_digital_art),
    ORIGAMI("origami", "Origami", SharedRes.images.img_style_preset_origami),
    LINE_ART("line-art", "Line Art", SharedRes.images.img_style_preset_line_art),
    ANALOG_FILM("analog-film", "Analog Film", SharedRes.images.img_style_preset_analog_film),
    CINEMATIC("cinematic", "Cinematic", SharedRes.images.img_style_preset_cinematic),
    ISOMETRIC("isometric", "Isometric", SharedRes.images.img_style_preset_isometric),
    LOW_POLY("low-poly", "Low Poly", SharedRes.images.img_style_preset_low_poly),
    TILE_TEXTURE("tile-texture", "Tile Texture", SharedRes.images.img_style_preset_tile_texture),
}