package pe.edu.upc.superherocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch
import pe.edu.upc.superherocompose.data.local.AppDatabase
import pe.edu.upc.superherocompose.data.model.Hero
import pe.edu.upc.superherocompose.data.remote.ApiClient
import pe.edu.upc.superherocompose.repository.HeroRepository
import pe.edu.upc.superherocompose.ui.theme.SuperHeroComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperHeroComposeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    val apiService = ApiClient.build()
    val context = LocalContext.current
    val favoriteDao = AppDatabase.getInstance(context).heroDao()
    val repository = HeroRepository(apiService, favoriteDao)
    val coroutineScope = rememberCoroutineScope()
    var name by remember { mutableStateOf("") }
    var heroes: List<Hero> by remember { mutableStateOf(listOf()) }
    val focusManager = LocalFocusManager.current


    Scaffold {
        Column {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                value = name,
                onValueChange = {
                    name = it
                },
                leadingIcon = {
                    Icon(Icons.Filled.Search, null)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search,
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        coroutineScope.launch {
                            heroes = repository.fetchHeroesByName(name)
                            focusManager.clearFocus()                        }
                    }
                )
            )
            HeroList(heroes, repository)
        }
    }
}

@Composable
fun HeroList(heroes: List<Hero> = listOf(), repository: HeroRepository) {
    LazyColumn {
        items(heroes) { hero ->
            HeroRow(hero, repository)
        }
    }
}

@Composable
fun HeroRow(hero: Hero, repository: HeroRepository) {

    var favorite by remember {
        mutableStateOf(false)
    }
    favorite = hero.favorite
    val coroutineScope = rememberCoroutineScope()

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        elevation = 2.dp
    ) {
        Row {
            HeroImage(hero)
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(7f)) {
                Text(hero.name)
                Text(hero.fullName)
            }
            IconButton(
                modifier = Modifier.weight(1f),
                onClick = {

                    coroutineScope.launch {
                        if (favorite) {
                            repository.delete(hero)
                        } else {
                            repository.insert(hero)
                        }
                        favorite = !favorite
                        hero.favorite = favorite
                    }

                }) {
                Icon(
                    Icons.Filled.Favorite,
                    null,
                    tint = if (favorite) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
                )
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun HeroImage(hero: Hero) {
    Image(
        painter = rememberImagePainter(hero.imageUrl),
        contentDescription = null,
        modifier = Modifier
            .size(92.dp)
            .clip(shape = RoundedCornerShape(4.dp)),
        contentScale = ContentScale.Crop
    )
}