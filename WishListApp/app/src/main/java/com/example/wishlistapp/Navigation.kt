package com.example.wishlistapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation(
    viewModel: WishViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(Screen.HomeScreen.route) {
            HomeView(navController)
        }
        composable(Screen.AddScreen.route + "/{id}",
            arguments = listOf(navArgument("id"){
                type = NavType.LongType
                defaultValue = 0L
                nullable = false
            })
            ) { entry ->

            //if the entry arguments are not null
            //take those entry arguments and unpack them then take the long value
            val id = if(entry.arguments != null) entry.arguments!!.getLong("id") else 0L

            AddEditDetailView(
                id = id,
                viewModel = viewModel,
                navController = navController
            )
        }
    }
}
