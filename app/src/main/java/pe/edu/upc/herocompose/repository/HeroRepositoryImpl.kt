/*
 * Designed and developed by 2021 jmayta1984 (Jorge Mayta)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pe.edu.upc.herocompose.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.edu.upc.herocompose.data.local.HeroDao
import pe.edu.upc.herocompose.data.model.Hero
import pe.edu.upc.herocompose.data.remote.HeroService
import pe.edu.upc.herocompose.utils.Resource
import javax.inject.Inject

class HeroRepositoryImpl @Inject constructor(
    private val heroService: HeroService,
    private val heroDao: HeroDao
) : HeroRepository {
    override suspend fun update(hero: Hero) = withContext(Dispatchers.IO) {
        hero.favorite = heroDao.fetchHeroById(hero.id) != null
        hero.fullName = hero.biography.fullName
        hero.imageUrl = hero.image.url
    }

    override suspend fun fetchHeroesByName(name: String): Resource<List<Hero>> =
        withContext(Dispatchers.IO) {
            val heroes: List<Hero>
            val response = heroService.fetchHeroesByName(name)
            if (response.isSuccessful && response.body() != null) {
                if (response.body()!!.response == "success") {
                    heroes = response.body()!!.results
                    for (hero in heroes) {
                        update(hero)
                    }
                    return@withContext Resource.Success(heroes)
                }
            }
            return@withContext Resource.Error("No data found")
        }

    override suspend fun fetchHeroById(id: String): Resource<Hero> = withContext(Dispatchers.IO) {
        val response = heroService.fetchHeroesById(id)
        val hero = response.body() as Hero
        update(hero)
        return@withContext Resource.Success(hero)
    }

    override suspend fun delete(hero: Hero) = withContext(Dispatchers.IO) {
        heroDao.delete(hero)
        hero.favorite = false
    }

    override suspend fun insert(hero: Hero) = withContext(Dispatchers.IO) {
        heroDao.insert(hero)
        hero.favorite = true
    }
}