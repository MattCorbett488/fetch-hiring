# Fetch Take Home

This is the implementation of this assignment [here](https://fetch-hiring.s3.amazonaws.com/mobile.html)

## Modules/Packages

Since this is fairly simple (for now), there is only the `app` module.  In a larger project I likely would have broken things up into a `network` module and a `data`/`common` module (for some networking, repositories, etc) and any more as needed.

### Network package

Contains all the network-related code. This is primarily the `HiringService` interface and `DefaultHiringService` implementation.  Providing an interface here allows us to give a dummy one for testing (so testing becomes way easier).  The network layer is backed by Ktor.

### DI package

Since this is a simple app (for now), I didn't do a full-blown dependency injection solution.  In a normal project I would look at using Hilt or Koin.  I just went with a simple `Dependencies` object this time, so it's more akin to a service locator

### Model package

Simple and straightforward - where the models live

### UI package

Where any view code will go; depending on reuse I might have broken this into a separate module (or a separate library if we had multiple apps and wanted to maintain consistent visuals and behaviors between them)

## Future considerations

### Database/local storage

Right now the app does not have any local storage (either `SharedPreferences` or a DB solution); depending on how quickly hires change, it could make sense to include Room so that we save the hire list and only update it every so often (allowing for a more robust offline mode, less bandwidth usage, and lower average latency)

### Tests

The app has minimal tests at the moment since the focus was on completing the requirements. The repo has the needed dependencies already (MockK for unit tests, Espresso for UI tests)

### Navigation

Since this is a single screen, there is no navigation in place.  The best candidate here is [compose navigation](https://developer.android.com/develop/ui/compose/navigation) since it now supports type safe argument passing

### Code coverage, linting, and static code analysis

None of that fancy stuff to enforce best practices and consistency, but adding [Ktlint](https://github.com/pinterest/ktlint) for linting, [Detekt](https://github.com/detekt/detekt) for static analysis, and [JaCoCo](https://www.eclemma.org/jacoco/) / [Kover](https://github.com/Kotlin/kotlinx-kover) for code coverage would be a good next step.

### Repositories and abstracted data layers

Right now the ViewModel knows directly about the service; we'd likely want to go with either a UseCase model or a Repository architecture to abstract that.  This would come in handy if we had more complex models that needed to be mapped from the API or database to business objects, or to abstract any database/network logic.
