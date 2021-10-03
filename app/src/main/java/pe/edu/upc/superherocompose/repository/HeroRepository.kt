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

package pe.edu.upc.superherocompose.repository

import pe.edu.upc.superherocompose.data.local.HeroDao
import pe.edu.upc.superherocompose.data.model.Hero
import pe.edu.upc.superherocompose.data.remote.HeroService

class HeroRepository(private val heroService: HeroService, private val heroDao: HeroDao) {

    private suspend fun update(hero: Hero) {
        hero.favorite = heroDao.fetchHeroById(hero.id) != null
        hero.fullName = hero.biography.fullName
        hero.imageUrl = hero.image.url
    }

    suspend fun fetchHeroesByName(name: String): List<Hero> {
        val response = heroService.fetchHeroesByName(name)
        if (response.isSuccessful && response.body() != null) {
            if (response.body()!!.response == "success") {
                val heroes = response.body()!!.results
                for (hero in heroes) {
                    update(hero)
                }
                return heroes
            }
        }
        return listOf()
    }

    suspend fun fetchHeroById(id: String): Hero {
        val response = heroService.fetchHeroesById(id)
        val hero = response.body() as Hero
        update(hero)
        return hero
    }

    suspend fun delete(hero: Hero) {
        heroDao.delete(hero)
        hero.favorite = false
    }

    suspend fun insert(hero: Hero) {
        heroDao.insert(hero)
        hero.favorite = true
    }
}