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
        startDestination = "HeroList"
    ) {

        composable("HeroList") {
            val viewModel: HeroesViewModel = hiltViewModel()
            Heroes(viewModel) {
                navController.navigate("HeroDetails/$it")
            }
        }

        composable(
            route = "HeroDetails/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id", "") as String
            val detailsViewModel: HeroDetailsViewModel = hiltViewModel()
            detailsViewModel.fetchHeroById(id)
            HeroDetails(detailsViewModel)
        }
    }
}