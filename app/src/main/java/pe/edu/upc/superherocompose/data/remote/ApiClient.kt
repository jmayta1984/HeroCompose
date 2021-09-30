package pe.edu.upc.superherocompose.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val API_BASE_URL = "https://www.superheroapi.com/api.php/10157703717092094/"

    var heroService: HeroService? = null

    fun build(): HeroService {
        val retrofit = Retrofit.Builder().baseUrl(API_BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).build()

        heroService = retrofit.create(HeroService::class.java)

        return heroService as HeroService

    }
}