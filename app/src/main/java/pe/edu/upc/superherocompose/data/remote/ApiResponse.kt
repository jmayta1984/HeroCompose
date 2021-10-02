package pe.edu.upc.superherocompose.data.remote

import pe.edu.upc.superherocompose.data.model.Hero

class ApiResponse(
    val response: String,
    val results: List<Hero>
)