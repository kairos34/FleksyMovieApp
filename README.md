# FleksyMovieApp
The Movie Database API integration application that lists popular/highest-rated movies and shows similar movies with selected movie in detail screen. It also has dark/light theme switching feature.

MVVM pattern used with Kotlin Flows as reactive programming library and Hilt as depencendy injection library, also Clean Code paradigm followed with SOLID principles using lots of Interfaces(to make it less dependent) and UseCases. Jetpack Compose Navigation used to navigate between screens. Unit tests written using MockWebServer(to simulate fake API Service) library.

![Fleksy Movie App](FleksyMovieApp.gif)

## Building the app
1.Open local.properties in the root directory

2.Add your [TMDB](https://www.themoviedb.org/) API key as follows

>API_KEY=ADD_YOUR_API_KEY_HERE

3.Run the app(debug).

`If you want to play with release flavor, you have to create your own signature!!!`

## Used Libraries 
- Jetpack Compose https://developer.android.com/jetpack/compose
- UI Pager https://google.github.io/accompanist/pager/
- Lottie Animation https://github.com/airbnb/lottie-android
- Landscapist https://github.com/skydoves/Landscapist
- Hilt https://developer.android.com/training/dependency-injection/hilt-android
- Retrofit https://square.github.io/retrofit/
- MockWebServer https://github.com/square/okhttp/tree/master/mockwebserver
- LeakCanary https://square.github.io/leakcanary/fundamentals/
