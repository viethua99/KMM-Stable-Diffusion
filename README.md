![KMP-Stable-Diffusion](docs/images/project-header.png)
<p align="center">
  <a href="https://github.com/viethua99/KMP-Stable-Diffusion/releases">
     <img src="https://img.shields.io/github/downloads/viethua99/KMP-Stable-Diffusion/total?color=3BB143" alt="Github - viethua99">
  </a>

  <a href="https://opensource.org/licenses/Apache-2.0">
     <img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-brown.svg"/>
  </a>

  <a href="https://github.com/viethua99">
     <img src="https://img.shields.io/badge/Github-viethua99-blueviolet?logo=github" alt="Github - viethua99">
  </a>

  <a href="https://www.linkedin.com/in/viet-hua-3255a2181/">
     <img src="https://img.shields.io/badge/Linkedin-Viet Hua-0077B5?logo=linkedin&logoColor=" alt="Linkedin - viethua99">
  </a>

</p>

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)

## 💡 Download
Go to the [Releases](https://github.com/viethua99/KMP-Stable-Diffusion/releases) to download the latest APK version.

## ️🎏 Features

| Text To Image                                                 | Image To Image | AI Inpainting |
|---------------------------------------------------------------|----------------|---------------|
| <img width="320" src="docs/images/android_tti_feature.gif" /> | Coming soon    | Coming soon   | 

## 👀 Screenshots

| Platform | Home (1)                                                       | Home (2)                                                       | Generate                                                       | Detail                                                         |
|----------|----------------------------------------------------------------|----------------------------------------------------------------|----------------------------------------------------------------|----------------------------------------------------------------|
| Android  | <img width="320" src="docs/images/android_screenshot_1.png" /> | <img width="320" src="docs/images/android_screenshot_2.png" /> | <img width="320" src="docs/images/android_screenshot_3.png" /> | <img width="320" src="docs/images/android_screenshot_4.png" /> | 
| iOS      | <img width="320" src="docs/images/ios_screenshot_1.png" />     | <img width="320" src="docs/images/ios_screenshot_2.png" />     | <img width="320" src="docs/images/ios_screenshot_3.png" />     | <img width="320" src="docs/images/ios_screenshot_4.png" />     | 


## 🎨 Support Styles

| Style        | Sample                                                                        | Style        | Sample                                                                         |
|--------------|-------------------------------------------------------------------------------|--------------|--------------------------------------------------------------------------------|
| Anime        | <img width="120" height="120" src="docs/images/img_style_anime.png" />        | Origami      | <img width="120" height="120" src="docs/images/img_style_origami.png" />       |
| 3D Model     | <img width="120" height="120" src="docs/images/img_style_model3d.png" />      | Line Art     | <img width="120" height="120" src="docs/images/img_style_line_art.png" />      |
| Photographic | <img width="120" height="120" src="docs/images/img_style_photographic.png" /> | Analog Film  | <img width="120" height="120" src="docs/images/img_style_analog_film.png" />   |
| Comic Book   | <img width="120" height="120" src="docs/images/img_style_comic_book.png" />   | Cinematic    | <img width="120" height="120" src="docs/images/img_style_cinematic.png" />     |
| Pixel Art    | <img width="120" height="120" src="docs/images/img_style_pixel_art.png" />    | Isometric    | <img width="120" height="120" src="docs/images/img_style_isometric.png" />     |
| Neon Punk    | <img width="120" height="120" src="docs/images/img_style_neon_punk.png" />    | Low Poly     | <img width="120" height="120" src="docs/images/img_style_low_poly.png" />      |
| Fantasy Art  | <img width="120" height="120" src="docs/images/img_style_fantasy_art.png" />  | Tile Texture | <img width="120" height="120"  src="docs/images/img_style_tile_texture.png" /> |
| Digital Art  | <img width="120" height="120" src="docs/images/img_style_digital_art.png" />  |

## 🛠 Setup
### Stability AI
<img src="docs/images/img_stabilityai_logo.png" align="right" width="12%"/>

This project using the [Stability AI API](https://stability.ai/) for constructing RESTful API. Stability AI provides a RESTful API interface to highly detailed objects built from thousands of lines of data related to text to images.

- Step 1: Create an account and generate an API key at [Stability AI](https://platform.stability.ai/docs/getting-started)

- Step 2: Paste the API key to your project `gradle.properties` file:
<pre><code class="lang-groovy">STABLE_DIFFUSION_API_KEY=YOUR_API_KEY_HERE</code></pre>

- Step 3: Sync project and run your app
### Android
Select `composeApp` configuration and run the project.
<img width="120" src="docs/images/composeapp-run.png" />

### iOS
Select `iOSApp` configuration and run the project.
<img width="120" src="docs/images/iosapp-run.png" />

## 📂 Technical Dependencies
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- [Composable Multiplatform](https://jb.gg/compose) for building common UI. It simplifies and accelerates UI development on Android, iOS and Desktop.
- [Koin](https://insert-koin.io/) for dependency injection.
- [Moko Resources](https://github.com/adrielcafe/voyager) for multiplatform string and images resources.
- [Ktor & Kotlin Serialization](https://ktor.io/) for constructing the REST APIs and paging network data.
- [SqlDelight](https://cashapp.github.io/sqldelight/2.0.1/) for local database.
- [Voyager](https://github.com/adrielcafe/voyager) for navigation and screen models.


## 📐 Project Structure
### Common Structure
```
├── composeApp
│   ├── androidMain
│   ├── commonMain
│   │   ├── kotlin/com/vproject/stablediffusion
│   │   │   ├── database
│   │   │   │   ├── dao                                      <––– Data Access Object (DAOs)
│   │   │   │   └── ...
│   │   │   ├── di                                           <––– Koin Depedenency Injection package
│   │   │   ├── model
│   │   │   ├── network
│   │   │   │    ├── StableDiffusionApi.kt                   <––– RESTFul APIs
│   │   │   │    └── ...
│   │   │   ├── presentation
│   │   │   │   ├── component
│   │   │   │   ├── screen
│   │   │   │   │   ├── generate                             <––– Generate Screen package
│   │   │   │   │   │   ├── GenerateScreen.kt                <––– Composable Generate Screen UIs
│   │   │   │   │   │   ├── GenerateModel.kt                 <––– Generate Screen View Model
│   │   │   │   │   │   └── GenerateUiState.kt               <––– Generate Sealed class UI State
│   │   │   │   │   └── ...
│   │   │   ├── repository
│   │   │   │   ├── ImageRepository.kt                       <––– Image repository to send requests
│   │   │   │   └── ...
│   │   │   └── util
│   │   ├── resources                                        <––– Moko Resources to store local images and strings
│   │   └── sqldelight/com/vproject/stablediffusion          <––– Local database entities
│   ├── desktopMain
│   └── iosMain
└── ...
```
### System Design
![System Design](docs/images/system-design.png)

## 😍 Find this repository useful?
Support it by joining __[stargazers](https://github.com/viethua99/KMP-Stable-Diffusion/stargazers)__ for this repository. :star: <br>
Also, __[follow me](https://github.com/viethua99)__ on GitHub for my next creations! 🤩

## 🔓 License

**KMP-Stable-Diffusion** is distributed under the terms of the Apache License (Version 2.0). See the
[license](LICENSE) for more information.