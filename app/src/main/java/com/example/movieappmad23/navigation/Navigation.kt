package com.example.movieappmad23.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieappmad23.screens.*

@Composable
fun Navigation() {
    val navController = rememberNavController()

    //val db = MovieDatabase.getDatabase(LocalContext.current)
    //val repo = MovieRepository(db.movieDao())
    //val factory = MoviesViewModelFactory(repo)
    //val viewModel: FavoritesViewModel = viewModel(factory = factory)

    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route){
            HomeScreen(navController = navController)
        }

        composable(Screen.FavoriteScreen.route) {
            FavoriteScreen(navController = navController)
        }
        
        composable(Screen.AddMovieScreen.route) {
            AddMovieScreen(navController = navController)
        }

        // build a route like: root/detail-screen/id=34
        composable(
            Screen.DetailScreen.route,
            arguments = listOf(navArgument(name = DETAIL_ARGUMENT_KEY) {type = NavType.LongType})
        ) { backStackEntry ->    // backstack contains all information from navhost
            DetailScreen(
                navController = navController,
                movieId = backStackEntry.arguments?.getLong(DETAIL_ARGUMENT_KEY))   // get the argument from navhost that will be passed
        }
    }
}