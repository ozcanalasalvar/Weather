
# Weather

![build](https://github.com/OzcanAlasalvar/Weather/actions/workflows/Build.yaml/badge.svg)

Weather is a full functional project to work around android components, architecture and tools for Android development.
<br><br>

<p align="center">
  <img src="https://github.com/OzcanAlasalvar/Weather/blob/main/art/search_dark.png" width="250">
  <img src="https://github.com/OzcanAlasalvar/Weather/blob/main/art/detail_dark.png" width="250">
  <img src="https://github.com/OzcanAlasalvar/Weather/blob/main/art/detail_night.gif" width="250">
</p>
<br><br>

This project uses [app modularization architecture](https://developer.android.com/topic/modularization) and  built entirely with Kotlin and Jetpack Compose. The weather feature module uses MVVM as software design patter for presentation layer.


<br><br>

<p align="center">
  <img src="https://github.com/OzcanAlasalvar/Weather/blob/main/art/serarch_light.png" width="250">
  <img src="https://github.com/OzcanAlasalvar/Weather/blob/main/art/home_light.png" width="250">
  <img src="https://github.com/OzcanAlasalvar/Weather/blob/main/art/detail_light.gif" width="250">
</p>



<br>

# Used technologies

- Application entirely written in [Kotlin](https://kotlinlang.org)
- Asynchronous processing using [Coroutines](https://kotlin.github.io/kotlinx.coroutines/) + [Flow](https://developer.android.com/kotlin/flow)
- Uses [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for dependency injection
- Uses [Room](https://developer.android.com/training/data-storage/room)
- [Retrofit](https://square.github.io/retrofit/) for network request

<br>

# CI/CD
Uses [Github Actions](https://docs.github.com/en/actions/learn-github-actions)

<br>

# Gradle
- [Convention Plugin](https://docs.gradle.org/current/samples/sample_convention_plugins.html)
- [Vesion Catalog](https://developer.android.com/build/migrate-to-catalogs)

Convetion plugins and version katalog are used for sharing build logic between submodules

<br>

# Testing
Uses [JUnit4](https://developer.android.com/training/testing/junit-rules), [Espresso](https://developer.android.com/training/testing/espresso), [Compose Ui Test](https://developer.android.com/jetpack/compose/testing) among other libraries for unit & instrumented tests.



<br><br>

### API keys

You need to supply API / client keys for the service the app uses.

- [OpenWeather](https://openweathermap.org/)

Once you obtain the key, you can set them in your `~/local.properties`:

```
# Get this from OpenWeather
API_KEY=<insert>
```
