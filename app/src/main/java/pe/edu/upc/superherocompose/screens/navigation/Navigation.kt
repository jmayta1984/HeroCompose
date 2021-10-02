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
import pe.edu.upc.superherocompose.screens.herolist.HeroList
import pe.edu.upc.superherocompose.screens.herolist.HeroListViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()

    val viewModel: HeroListViewModel = hiltViewModel()
    val detailsViewModel: HeroDetailsViewModel = hiltViewModel()


    NavHost(
        navController = navController,
        startDestination = "HeroList"
    ) {


        composable("HeroList") {
            HeroList(viewModel) {
                navController.navigate("HeroDetails/$it")
            }
        }

        composable(
            route = "HeroDetails/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id", "") as String
            detailsViewModel.fetchHeroById(id)
            HeroDetails(detailsViewModel)
        }
    }
}