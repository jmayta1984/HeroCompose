package pe.edu.upc.superherocompose.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroService {
    @GET("search/{name}")
    suspend fun fetchHeroesByName(@Path("name") name: String): Response<ApiResponse>
}