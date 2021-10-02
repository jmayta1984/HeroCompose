package pe.edu.upc.superherocompose.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import pe.edu.upc.superherocompose.data.local.AppDatabase
import pe.edu.upc.superherocompose.data.model.Hero
import pe.edu.upc.superherocompose.data.remote.ApiClient
import pe.edu.upc.superherocompose.repository.HeroRepository

@Composable
fun HeroDetails(id: String) {

    val apiService = ApiClient.build()
    val context = LocalContext.current
    val favoriteDao = AppDatabase.getInstance(context).heroDao()
    val repository = HeroRepository(apiService, favoriteDao)
    var hero by remember {
        mutableStateOf(Hero())
    }

    LaunchedEffect(key1 = Unit, block = {
        hero = repository.fetchHeroById(id)
    })


    Column {
        HeroImage(hero)
        Spacer(modifier = Modifier.width(8.dp))
        Text(hero.name)
    }
}