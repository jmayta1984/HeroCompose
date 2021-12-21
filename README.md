<h1 align="center">HeroCompose</h1>

<p align="center">  
A demo app using compose based on modern Android tech-stacks and MVVM architecture. Fetching data from network and integrating persisted data in the database via repository pattern.
</p>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="http://developer.android.com/index.html"><img alt="Platform" src="https://img.shields.io/badge/platform-Android-green.svg"/></a>
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
  </p>

## Screenshots
<p align="center">
<img src="https://user-images.githubusercontent.com/38768001/135744698-363a3f21-843d-4cbb-b861-6970766ac0d1.png" width=32%/>
<img src="https://user-images.githubusercontent.com/38768001/135744933-eb5fe0c6-5eb6-4820-9759-5b1ec77085b3.png" width=32%/>
<img src="https://user-images.githubusercontent.com/38768001/135744699-ca34dd3b-be30-4dc1-9b25-b6e057ed53d7.png" width=32%/>
</p>

## How to build on your environment
Add your [SuperHero API documentation](https://superheroapi.com/)'s API key in local.properties file.
```xml
hero_api_key=YOUR_API_KEY
```

## Tech stack & Open-source libraries
- Minimum SDK level 21
- 100% [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous.
- JetPack
  - Compose - a modern toolkit for building native Android UI.
  - LiveData - notify domain layer data to views.
  - Lifecycle - dispose observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Room Persistence - construct database.
- Architecture
  - MVVM Architecture (Declarative View - ViewModel - Model)
  - Repository pattern
- Material Design
- [Retrofit2](https://github.com/square/retrofit) - construct the REST APIs.

## MAD Score
![summary](https://user-images.githubusercontent.com/38768001/135744388-2aee8e8c-c9ef-4ba8-a366-6e1790a1d90e.png)
![jetpack](https://user-images.githubusercontent.com/38768001/135744387-e50811ce-ea39-46e7-810a-dfea3ca25574.png)

## Architecture
HeroCompose is based on MVVM architecture and repository pattern.
![architecture](https://user-images.githubusercontent.com/38768001/134121196-9a278e99-fc4c-426a-a136-d564ebc2915d.png)

## Slides
![Slides](https://user-images.githubusercontent.com/38768001/146867547-b5e0a3d6-fe12-45ce-ad87-a8b95e6be3bc.png)</br>
Find the slides in the following [link](https://docs.google.com/presentation/d/1PNRzfWTVbRv7K2cTZUBKqnN8KoRrR740XJBHi49Hvfs/edit#slide=id.g10968617ed6_0_0).

# License
```xml
Designed and developed by 2021 jmayta1984 (Jorge Mayta)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
