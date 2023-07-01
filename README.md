<img src="docs/images/brush-ai-readme-cover.png"/>
<h1 align="center"><b>Brush AI</b></h1>
<p align="center">
  <i align="center">🚀 Instantly generate high-quality images based on your text prompt 🚀</i>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0">
     <img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/>
  </a>

  <a href="https://github.com/viethua99">
     <img src="https://img.shields.io/badge/Github-viethua99-blueviolet?logo=github" alt="Github - viethua99">
  </a>
</p>

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)


<img src="docs/images/brush-ai-showcase.gif" align="right" width="280"/>

## Features

- Type in your prompt and start generating images.
- Multiple styles to customize your images.
- You are able to download the generated images.
- Support 2 languages (English and Vietnamese).
- Support Light / Dark mode.

## Technical Dependencies
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- Jetpack
    - Composable: 
    - ViewModel: Manages UI-related data holder and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
    - Room: Constructs Database by providing an abstraction layer over SQLite to allow fluent database access.
    - Navigation Component: Constructs Database by providing an abstraction layer over SQLite to allow fluent database access.
    - [Hilt](https://dagger.dev/hilt/): for dependency injection.
- Architecture
    - MVVM Architecture (View - ViewModel - Model)
    - Repository Pattern
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit): Construct the REST APIs and paging network data.
- [ksp](https://github.com/google/ksp): Kotlin Symbol Processing API.
- [Coil](https://github.com/coil-kt/coil): Loading images from network.


## Architecture
**Brush AI** is based on the MVVM architecture, which follows the [Google's official architecture guidance](https://developer.android.com/topic/architecture).

![architecture](docs/images/figure0.png)

The overall architecture of **Brush AI** is composed of two layers; the UI layer and the data layer. Each layer has dedicated components and they have each different responsibilities, as defined below:

**Brush AI** was built with [Guide to app architecture](https://developer.android.com/topic/architecture), so it would be a great sample to show how the architecture works in real-world projects.


### Architecture Overview

![architecture](docs/images/figure1.png)

- Each layer follows [unidirectional event/data flow](https://developer.android.com/topic/architecture/ui-layer#udf); the UI layer emits user events to the data layer, and the data layer exposes data as a stream to other layers.
- The data layer is designed to work independently from other layers and must be pure, which means it doesn't have any dependencies on the other layers.

With this loosely coupled architecture, you can increase the reusability of components and scalability of your app.

### UI Layer

![architecture](docs/images/figure2.png)

The UI layer consists of UI elements to configure screens that could interact with users and [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) that holds app states and restores data when configuration changes.
- UI elements observe the data flow via [DataBinding](https://developer.android.com/topic/libraries/data-binding), which is the most essential part of the MVVM architecture.
- With [Bindables](https://github.com/skydoves/bindables), which is an Android DataBinding kit for notifying data changes, you can implement two-way binding, and data observation in XML very clean.

### Data Layer

![architecture](docs/images/figure3.png)

The data Layer consists of repositories, which include business logic, such as querying data from the local database and requesting remote data from the network. It is implemented as an offline-first source of business logic and follows the [single source of truth](https://en.wikipedia.org/wiki/Single_source_of_truth) principle.<br>

**Brush AI** is an offline-first app is an app that is able to perform all, or a critical subset of its core functionality without access to the internet.
So users don't need to be up-to-date on the network resources every time and it will decrease users' data consumption. For further information, you can check out [Build an offline-first app](https://developer.android.com/topic/architecture/data-layer/offline-first).

## Modularization

![architecture](docs/images/figure4.png)

**Brush AI** adopted modularization strategies below:

- **Re-usability**: Modularizing reusable codes properly enable opportunities for code sharing and limits code accessibility in other modules at the same time.
- **Parallel Building**: Each module can be run in parallel and it reduces the build time.
- **Strict visibility control**: Modules restrict to expose dedicated components and access to other layers, so it prevents they're being used outside the module
- **Decentralized focusing**: Each developer team can assign their dedicated module and they can focus on their own modules.

For more information, check out the [Guide to Android app modularization](https://developer.android.com/topic/modularization).

## Stable Diffusion API

<img src="https://media.licdn.com/dms/image/C560BAQHKCfkGo3NAFg/company-logo_200_200/0/1674489509554?e=2147483647&v=beta&t=gQQAyRwXUtoKjaxepavRkBsaxjY0L-qcXc9o-eykCYI" align="right" width="12%"/>

Brush AI using the [Stable Diffusion API](https://stablediffusionapi.com/) for constructing RESTful API.<br>
StableDiffusionAPI provides a RESTful API interface to highly detailed objects built from thousands of lines of data related to text to images.

## Find this repository useful? :heart:
Support it by joining __[stargazers](https://github.com/viethua99/BrushAI/stargazers)__ for this repository. :star: <br>
Also, __[follow me](https://github.com/viethua99)__ on GitHub for my next creations! 🤩


## License

**Brush AI** is distributed under the terms of the Apache License (Version 2.0). See the
[license](LICENSE) for more information.