package pe.edu.upc.superherocompose.repository

import pe.edu.upc.superherocompose.data.local.HeroDao
import pe.edu.upc.superherocompose.data.model.Hero
import pe.edu.upc.superherocompose.data.remote.HeroService

class HeroRepository(private val heroService: HeroService, private val heroDao: HeroDao) {

    suspend fun fetchHeroesByName(name: String): List<Hero> {
        val response = heroService.fetchHeroesByName(name)
        if (response.isSuccessful && response.body() != null) {
            if (response.body()!!.response == "success") {
                val heroes = response.body()!!.results
                for (hero in heroes) {
                    hero.favorite = heroDao.fetchHeroById(hero.id) != null
                    hero.fullName = hero.biography.fullName
                    hero.imageUrl = hero.image.url
                }
                return heroes
            }
        }
        return listOf()
    }

    suspend fun fetchHeroById(id: String): Hero {
        val response = heroService.fetchHeroesById(id)
        val hero = response.body() as Hero
        hero.imageUrl = hero.image.url
        hero.fullName = hero.biography.fullName
        return hero
    }

    suspend fun delete(hero: Hero) {
        heroDao.delete(hero)
    }

    suspend fun insert(hero: Hero) {
        heroDao.insert(hero)
    }
}