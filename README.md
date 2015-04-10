# GoEuroMobile

GoEuroMobile is a test assignment application. The description can be found here https://github.com/goeuro/mobile-test

Demo movie can be found here: https://www.youtube.com/watch?v=dEg6WfzOySA&feature=youtu.be

A part of the application was created using `TDD` approach. In order to make this happen the MVP and DI architectural patters are used.
Tests written with plain unit tests (`JUnit4`) and with `Robolectic` library where not mocked version of object instances from Android Framework is under consideration (like `DistanceComparatorTest.java`)

Scheduled location updates and Google Play Services availability wasn't implemented deliberately.

The following 3rd party libraries were used:

* `Dagger` as Dependency Injection library
* `EventBus` from `Greenrobot` as event bus
* `Android Priority Job Queue` for queries/prioritizing/execution of background operations
* `Retrofit` for network interface
* `OkHttp` as network library
* `Gson` for `JSONs` parsing
* `IntelliJ Annotations` to reduce an amount for `null` checks
* `ButterKnife` to reduce amount of boilerplate code for `View` part
* `Timber` for logging
* `Crouton` for user notifications
* `datetimepicker` for calendar widget
* `Harmcrest` for human readable test assertions
* `Mockito` for mocking

# Get Project

`git clone https://github.com/Yougin/GoEuroMobile`

# Quick start

1. Builds the project `./gradlew clean assembleDebug`
2. You can run tests from IntellijIDEA / Android Studio or `./gradlew clean testDebug`
