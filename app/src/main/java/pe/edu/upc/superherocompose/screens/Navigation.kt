package pe.edu.upc.superherocompose.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "HeroList"
    ) {

        composable("HeroList") {
            HeroList{
                navController.navigate("HeroDetails/$it")
            }
        }

        composable(
            route = "HeroDetails/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id", "") as String
            HeroDetails(id)
        }
    }
}