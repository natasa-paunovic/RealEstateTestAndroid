<h1><b>Project Overview</b></h1>

This project demonstrates a modern architecture with focus on:

* MVVM + Clean Architecture
* Jetpack Compose
* Paging 3
* Coil for images
* Coroutine and Kotlin Flow
* Repository pattern
* Mapper pattern
* Local datastore 
* DI (Using Koin)
* Testing strategies (Unit, Integration i UI testing)


<img width="540" height="1140" alt="Screenshot_20260221_162210" src="https://github.com/user-attachments/assets/9fa0a1f4-7ca1-43cc-a097-3e26dfcc76b1" />




<h1><b>Project Architecture style</b></h1>

  <b> Data Layer</b>
  
Responsible for handling data sources (network, local storage, paging)

* API – Retrofit service definitions with OkHttp and Gson Converter
* DTOs – domain transfer object based on network response models 
* Paging3 – PropertyPagingSource for pagination 
* Repository Implementation – Concrete implementation of domain repository 
* DataStore – Local persistence for favourite properties


<b> Domain Layer</b>

This layer is independent of Android framework and contains business logic and core models.

* Models – Property, Price 
* Repository Interface – PropertyRepository 
* Preferences Abstraction – PropertyFavouritesPreference

<b>Presentation Layer</b>

Handles UI and state management.

* UI (Jetpack Compose) – Screens, reusable component (for card property)
* ViewModel – PropertyViewModel (state holder and logic coordination)


<h1><b>Testing Stack</b></h1>

Unit Testing
* JUnit 
* MockK 
* kotlinx-coroutines-test 

Test coverage includes:
* Mapper logic 
* ViewModel behavior 
* Repository contracts 
* PagingSource logic
  
Integration Testing
* MockWebServer 
* Retrofit test instance 
* JSON contract validation 

UI Testing
* Espresso 
* AndroidJUnit4 


<h1><b> Dependency Management </b></h1>

* Gradle Version Catalog (libs.versions.toml)
* Structured test dependency separation 
* Dedicated test and androidTest source sets

<h1><b> Scalability </b></h1>

This architecture allows easy extension. Future additions could include:

* Room database 
* Multi-module separation (feature modules)

<h1><b> Future Improvements </b></h1>

For future potential improvements it can be integrated:

* NavGraph3 for clearly separation screens between property listings and favourite properties. Based on API response models it could be implemented properety details screen as separate screen.
* CI pipeline (GitHub Actions) 
* Testing Flow emissions using Turbine, testing loading states
* Feature-based modularization 


