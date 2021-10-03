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

package pe.edu.upc.superherocompose.screens.herodetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import pe.edu.upc.superherocompose.data.model.Hero

@Composable
fun HeroDetails(viewModel: HeroDetailsViewModel) {
    val hero by viewModel.hero.observeAsState(Hero())
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Spacer(modifier = Modifier.height(128.dp))
        HeroImage(hero)
        Spacer(modifier = Modifier.height(16.dp))
        HeroHeader(hero)
        Spacer(modifier = Modifier.height(16.dp))
        HeroBiography(hero)
        Spacer(modifier = Modifier.height(16.dp))
        HeroPowerStats(hero)
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun HeroHeader(hero: Hero) {
    Text(
        text = hero.name,
        style = MaterialTheme.typography.h5,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun HeroBiography(hero: Hero) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp),
        elevation = 2.dp
    ) {
        Column {
            Text(
                text = "Biography",
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(8.dp)
            )
            Spacer(modifier = Modifier.heightIn(8.dp))
            hero.biography.let {
                Text(
                    "Full name: ${it.fullName}",
                    style = MaterialTheme.typography.caption,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Place of birth: ${it.birthPlace}",
                    style = MaterialTheme.typography.caption,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Publisher: ${it.publisher}",
                    style = MaterialTheme.typography.caption,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Spacer(modifier = Modifier.heightIn(8.dp))
        }
    }
}

@Composable
fun HeroPowerStats(hero: Hero) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp),
        elevation = 2.dp
    ) {
        Column {
            Text(
                text = "Power Stats",
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(8.dp)
            )
            Spacer(modifier = Modifier.heightIn(8.dp))
            HeroPower("Intelligence", hero.powerStats.intelligence)
            HeroPower("Strength", hero.powerStats.strength)
            HeroPower("Speed", hero.powerStats.speed)
            HeroPower("Durability", hero.powerStats.durability)
            HeroPower("Power", hero.powerStats.power)
            HeroPower("Combat", hero.powerStats.combat)
        }
    }
}

@Composable
fun HeroPower(key: String, value: String) {
    value.toFloatOrNull()
        ?.let {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    key,
                    style = MaterialTheme.typography.caption,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .weight(1f)
                )
                Slider(
                    value = it,
                    onValueChange = {},
                    valueRange = 0f..100f,
                    modifier = Modifier.weight(3f)
                )
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
            .size(256.dp)
            .clip(shape = RoundedCornerShape(4.dp)),
        contentScale = ContentScale.Crop
    )
}