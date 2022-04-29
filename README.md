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
- Open your project in Android Studio and select File > Settings... > Build, Execution, Deployment > Build Tools > Gradle (Android Studio > Preferences... > Build, Execution, Deployment > Build Tools > Gradle on a Mac).
- Under Gradle JDK, choose the Embedded JDK option or you could add a an existing jdk.
- Supports any device with a minimum SDK of 23.
- Tested and developed using Galaxy Nexus API 30 emulator.

## What areas of the app did you focus on?
I focused more on the architecture so as to come up with a robust app and, at the same time achieve less boilerplate code. In this project, I spent time on handling errors in the app in an efficient way and avoiding boilerplate code. I am always concerned about how scalable and testable the app I develop will be. 

## What was the reason for your focus? What problems were you trying to solve?
I believe choosing the right architecture plays a vital role in the development lifecycle. This defines the scalability, robustness, and testability of the app. I was trying to come up with an efficient app and I believe I was able to cover most of it. Using app startup (efficiency), global  error handling (reducing boilerplate code), and hilt (reusable, refactorable, testable).

## How long did you spend on this project? 
Around 6-7 hours. Spread it over 3 days as I was building this app after my work hours. I really enjoyed while working on this project. I didn't just stick with my way of doing things but have gone ahead to try out new things, like different ways to handle errors in the application. So, it was great fun and satisfying to see how nicely it turned out. 

## Did you make any trade-offs for this project? What would you have done differently with more time?
- Follow the repository pattern, using a data mapper, which I believe in the long run, will reduce technical debt and make it easier to maintain and test. 
- Didn't create any base fragments, which I believe not only makes the development faster, but also reduces boilerplate code. 
- Would have made this into a muti-module app.
- Use resource files like styles extensively. And thus, it will be easier later to handle, especially if we need to use different themes. 
- Data cached in ViewModel, but should have handled that in the repository as ViewModel shouldn't know anything about if or how the data is cached. (Didn't focus a lot on this as this should be a small demo project)

## What do you think is the weakest part of your project?
- If we were to extend this app and as it grows, the app will get complicated as the base is not quite robust yet. I haven't concentrated much on bitmap loading which is known for its memory conception issue. I depended on the coil library for image loading, keeping in mind that a lot of images needed to be handled and that the library uses coroutine in the back. I would also add a custom intercepter to the coil initializer to capture errors if the call fails. I would also try Picasso, as we could easily specify a custom transformation. I'll spent time researching the benefits and drawbacks of using one library over another, especially if an app requires loading of a large number of images.
- Test, test, test: I have written unit tests, but that is never enough. I didn't check for any memory leaks or how many times the layout is drawn (believe avoided re-draw using DiffUtil and flat layouts).

## Did you copy any code or dependencies? Please make sure to attribute them here!
I recently came across this [article](https://proandroiddev.com/modeling-retrofit-responses-with-sealed-classes-and-coroutines-9d6302077dfe) lately and liked how the author handled the error.  I'm glad I found this and can see this coming to life in this project.

## Is there any other information you’d like us to know?
I believe we have covered most of the important aspects already through this fun small project. There is one thing I really want you all to know. I would like to express my interest in working with the Terminal API team, because l believe your team is made up of innovative developers and I have seen Square gaining so much success in a relatively short time. Like you, I am innovative in my approach to tasks and would love to work with like minded professionals. I am looking forward to learning more about Square's Terminal API team and more about the architecture and tech stacks the team uses.