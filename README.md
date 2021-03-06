# Movie App
A simple movie application that demos list-detail processes with latest android technologies

[![Kotlin Version](https://img.shields.io/badge/Kotlin-1.4.20-blue.svg)](https://kotlinlang.org)
[![AGP](https://img.shields.io/badge/AGP-4.1.1-blue?style=flat)](https://developer.android.com/studio/releases/gradle-plugin)
[![Gradle](https://img.shields.io/badge/Gradle-6.5.1-blue?style=flat)](https://gradle.org)

A simple List-Detail sample project that presents a modern, 2021 approach to
[Android](https://en.wikipedia.org/wiki/Android_(operating_system)) application development with up to date tech-stack.

The goal of the project is to demonstrate best practices by using up to date tech-stack and presenting modern Android application
[Architecture](#architecture) that is multi-module, scalable, maintainable, and testable.

## Project characteristics

This project brings to table set of best practices, tools, and solutions:

* 100% [Kotlin](https://kotlinlang.org/)
* Modern architecture (Model-View-ViewModel, Model-View-Intent)
* [Android Jetpack](https://developer.android.com/jetpack)
* Reactive UI
* Testing (Unit, UI)
* Material design
* CI with GitHub Actions

## Tech-stack
Min API level is set to [`21`](https://android-arsenal.com/api?level=21), so the presented approach is suitable for over
[85% of devices](https://developer.android.com/about/dashboards) running Android. This project takes advantage of many
popular libraries and tools of the Android ecosystem. Most of the libraries are in the stable version unless there is a
good reason to use non-stable dependency.

* Tech-stack
    * [Kotlin](https://kotlinlang.org/) + [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) + [Flow](https://developer.android.com/kotlin/flow) - For synchronous and reactive programming.
    * [Retrofit](https://square.github.io/retrofit/) - networking. Known to be lighter than Retrofit.
    * [Jetpack](https://developer.android.com/jetpack)
        * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform an action when lifecycle state changes
        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related data in a lifecycle conscious way
  *   [Glide](https://github.com/bumptech/glide) - image loading library with better memory management for pretty large list
* Architecture
    * Clean Architecture (at module level)
    * MVVM + MVI (presentation layer)
    * [Android Architecture components](https://developer.android.com/topic/libraries/architecture) ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), [LiveData](https://developer.android.com/topic/libraries/architecture/livedata), [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation), [SafeArgs](https://developer.android.com/guide/navigation/navigation-pass-data#Safe-args) plugin)
* Tests
    * [Unit Tests](https://en.wikipedia.org/wiki/Unit_testing) ([JUnit](https://junit.org/junit4/))
    * [Mockk](https://mockk.io/)
* Gradle
    * [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html)

### Design decisions

Read related articles to have a better understanding of underlying design decisions and various trade-offs.

## What this project does not cover?

The interface of the app utilizes some of the modern material design components, however, is deliberately kept simple to
focus on application architecture.

## Getting started

There are a few ways to open this project.

### Android Studio

1. `Android Studio` -> `File` -> `New` -> `From Version control` -> `Git`
2. Enter `https://github.com/SunnyBe/MovieApp.git` into URL field
3. Provide `MOVIE_APP_KEY="<value goes here>"` in the `gradle.properties`
4. Provide `MOVIE_ACCESS_TOKEN="<value goes here>"` in the `gradle.properties`
5. Provide `MOVIE_API_KEY="<value goes here>"` in the `gradle.properties`

### Command-line + Android Studio

1. Run `git clone https://github.com/SunnyBe/MovieApp.git` to clone project
2. Go to `Android Studio` -> `File` -> `Open` and select cloned directory
3. Provide `MOVIE_APP_KEY="<value goes here>"` in the `gradle.properties`
4. Provide `MOVIE_ACCESS_TOKEN="<value goes here>"` in the `gradle.properties`
5. Provide `MOVIE_API_KEY="<value goes here>"` in the `gradle.properties`

## Inspiration

This is project is a sample, to inspire you and should handle most of the common cases, but please take a look at
additional resources.

## Known issues
- `ktlint` `import-ordering` rule conflicts with IDE default formatting rule, so it have to be [disabled](.editorconfig)
- False positive "Unused symbol" for a custom Android application class referenced in AndroidManifest.xml file ([Issue](https://youtrack.jetbrains.net/issue/KT-27971))
- False positive "Function can be private" ([Issue](https://youtrack.jetbrains.com/issue/KT-33610))
- Unit tests are running in IDE but fail after running gradle task because of missing Agrs class ([Issue](https://issuetracker.google.com/issues/139242292))

## Contribute

Want to contribute? Check our [Contributing](CONTRIBUTING.md) docs.

## Author

[![Follow me](https://pixabay.com/images/id-1606916/)](https://twitter.com/sunday_nectar)

[![Follow me](https://img.shields.io/twitter/follow/sunday_nectar?style=social)](https://twitter.com/sunday_nectar)

## License
```
MIT License

Copyright (c) 2020 Sunday Ndu

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN
NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF  TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```
