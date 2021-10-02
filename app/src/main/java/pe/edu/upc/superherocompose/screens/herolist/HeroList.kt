package pe.edu.upc.superherocompose.screens.herolist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import pe.edu.upc.superherocompose.data.model.Hero

@Composable
fun HeroList(viewModel: HeroListViewModel, selectHero: (String) -> Unit) {

    var name by remember {
        mutableStateOf("")
    }
    val heroes: List<Hero> by viewModel.heroes.observeAsState(listOf())
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
                        viewModel.fetchHeroesByName(name)
                        focusManager.clearFocus()
                    }
                )
            )
            HeroList(heroes, viewModel, selectHero)
        }
    }
}

@Composable
fun HeroList(
    heroes: List<Hero> = listOf(),
    viewModel: HeroListViewModel,
    selectHero: (String) -> Unit
) {
    LazyColumn {
        items(heroes) { hero ->
            HeroRow(hero, viewModel, selectHero)
        }
    }
}

@Composable
fun HeroRow(hero: Hero, viewModel: HeroListViewModel, selectHero: (String) -> Unit) {

    var favorite by remember {
        mutableStateOf(false)
    }
    favorite = hero.favorite

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        elevation = 2.dp
    ) {
        Row(modifier = Modifier.clickable {
            selectHero(hero.id)
        }) {
            HeroImage(hero)
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(7f)) {
                Text(hero.name)
                Text(hero.fullName)
            }
            IconButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    if (favorite) {
                        viewModel.delete(hero)
                    } else {
                        viewModel.insert(hero)
                    }
                    favorite = !favorite
                    hero.favorite = favorite
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