# World Now
![Made in](https://img.shields.io/badge/made%20in-kotlin-blue.svg)
![Pull Request](https://img.shields.io/badge/pull--request-welcome-green.svg)

This is a news app make purely in Kotlin and uses the Architecture Components, Dagger2 and RxJava

## Dagger-Android & RxJava

The app is built using `dagger.android` and `RxJava`. Below is the brief about the packages in the app:
  * `di` : This package contains all the files related to dependency injection
    * `component` : It contains the `AppComponent` that is responsible for generating all the code for injection and connecting the modules to injections.
    * `module` : It contains the Modules that provide the dependencies needed by the app.
    * `qualifier` : It contains the `ViewModelKey` `@MapKey` responsible for the injection in the `ViewModel`
  * `network` : This package contains a `NewsInterface` that is used by `Retrofit` to make network calls.
  * `ui` : It contains all the classes related to Android ui. For ex: `Activity`, `ViewModel`, `Adapter` etc.
    * `base` : Contains the `BaseActivity` that will be inherited by all the other activities.
    * `news` : Contains the `Activity` and `ViewModel` for the news.
  * `utils` : It contains the utility classes such as a `ClickListener`, `DataClasses` etc.

## Contributions

Found a bug? Just create an `issue` and once assigned, start working on it!

You feel that the app can be improved somehow? Maybe the architecture can be made more rebust so that the app is testable? Feel free to `fork` it and create a `Pull Request`.

Made with 💙 in Kotlin
