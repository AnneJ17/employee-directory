<h1 align="center">Employee Directory App</h1>

<p align="center">  
This app demonstrates modern Android development with Hilt, Coroutines, Flow, Jetpack ( ViewModel, Startup), and Material Design based on the MVVM architecture.
</p>
</br>

## Tech stack & Open-source libraries
- Minimum SDK level 23
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- [Hilt](https://dagger.dev/hilt/) for dependency injection.
- Jetpack
  - App Startup - Initialize components at application startup (use single content provider).
  - Lifecycle - Observe Android lifecycles and handle UI states accordingly upon lifecycle changes.
  - ViewModel - Manages UI-related data holders and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
  - ViewBinding - Creates direct references to all views that have an ID.
- Architecture - MVVM Architecture (View - ViewBinding - ViewModel - Model)
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - Construct the REST APIs.
- [Moshi](https://github.com/square/moshi/) - A modern JSON library for Kotlin and Java.
- [Turbine](https://github.com/cashapp/turbine) - A small testing library for kotlinx.coroutines Flow.
- [Logcat](https://github.com/square/logcat) - A Kotlin API for logging built on top of `Log` class.

## Setup
>Note: To compile, build, and run this app, I used Android Studio Dolphin | 2021.3.1 Canary 9 with Java 11.
- Open Terminal and change the current working directory to the location where you want the cloned directory. Then type in the below command and press Enter to create your local clone.
  ```
  $ git clone https://github.com/AnneJ17//employee-directory.git
  ```
- Now open the project in Android Studio and select File > Settings... > Build, Execution, Deployment > Build Tools > Gradle (Android Studio > Preferences... > Build, Execution, Deployment > Build Tools > Gradle on a Mac).
- Under Gradle JDK, choose the Embedded JDK option or you could add a an existing jdk.
- Supports any device with a minimum SDK of 23.
- Tested and developed using Galaxy Nexus API 30 emulator.
