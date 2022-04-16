# Project Structure

- `dataSource` : storage implementations
- `di` : dependence injections configurations
- `model` : application data models
- `network` : configurations to create communications
- `repository` : middleware between viewModels and data
- `ui` : layouts implementations (activities/fragments)
- `worker` : services that works in background

# Libraries

- [Dagger](https://github.com/google/dagger) : dependence injections
- [Picasso](https://github.com/square/picasso) : load and cache images by url
- [Retrofit](https://github.com/square/retrofit) : make http communications
- [RxJava](https://github.com/ReactiveX/RxJava) : to be more easy asynchronous implementations
- [Play Service Location](https://developers.google.com/android/guides/setup) : get user location
- [WorkManager](https://developer.android.com/jetpack/androidx/releases/work) : schedule works in background
- [Shared Preference](https://developer.android.com/training/data-storage/shared-preferences) : save only last weather

# App Description

Basically, the app configures all services and tries to start the WeatherWork (background service) as a PeriodicWork if it is not running yet.

The work will save the new weather obtained in local data storage. The view will get the weather from storage. All data are stored using SharedPreference because for this situation we need to save only last Weather but in case to improve the solution it is possible to change UserPreference to another likes Room, for example, inside DataStorageRepository.