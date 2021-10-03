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

package pe.edu.upc.superherocompose.screens.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pe.edu.upc.superherocompose.screens.herodetails.HeroDetails
import pe.edu.upc.superherocompose.screens.herodetails.HeroDetailsViewModel
import pe.edu.upc.superherocompose.screens.heroes.Heroes
import pe.edu.upc.superherocompose.screens.heroes.HeroesViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HeroList.route
    ) {

        composable(Routes.HeroList.route) {
            val viewModel: HeroesViewModel = hiltViewModel()
            Heroes(viewModel) {
                navController.navigate("${Routes.HeroDetails.route}/$it")
            }
        }

        composable(
            route = Routes.HeroDetails.routeWithArgument,
            arguments = listOf(navArgument(Routes.HeroDetails.argument) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString(Routes.HeroDetails.argument, "") as String
            val detailsViewModel: HeroDetailsViewModel = hiltViewModel()
            detailsViewModel.fetchHeroById(id)
            HeroDetails(detailsViewModel)
        }
    }
}

sealed class Routes(val route: String) {
    object HeroList : Routes("HeroList")
    object HeroDetails : Routes("HeroDetails") {
        const val routeWithArgument = "HeroDetails/{id}"
        const val argument = "id"
    }
}