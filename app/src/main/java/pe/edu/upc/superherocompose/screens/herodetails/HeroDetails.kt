package pe.edu.upc.superherocompose.screens.herodetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pe.edu.upc.superherocompose.data.model.Hero
import pe.edu.upc.superherocompose.screens.herolist.HeroImage

@Composable
fun HeroDetails(viewModel: HeroDetailsViewModel) {
    val hero by viewModel.hero.observeAsState(Hero())

    Column {
        HeroImage(hero)
        Spacer(modifier = Modifier.width(8.dp))
        Text(hero.name)
    }
}