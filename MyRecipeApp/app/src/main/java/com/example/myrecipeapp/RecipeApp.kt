package com.example.myrecipeapp

import RecipeScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(
    navController: NavHostController
) {
    
    val recipeViewModel: MainViewModel = viewModel()
    
    val viewState by recipeViewModel.categoriesState

    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route){
        composable(
            route = Screen.RecipeScreen.route
        ){
            RecipeScreen(viewState = viewState, navigateToDetail = {
                //This part is responsible for passing data from the current screen to the detail screen.
                //It utilizes the savedStateHandle, which is a component of the Compose Navigation Framework.

                navController.currentBackStackEntry?.savedStateHandle?.set("cat", it)
                navController.navigate(Screen.DetailScreen.route)

            })
        }

        composable(route = Screen.DetailScreen.route){
            val category = navController.previousBackStackEntry?.savedStateHandle?.get<Category>("cat")?:Category(
                "",
                "",
                "",
                "")
            CategoryDetailScreen(category = category) //actual composable which is our Category Detail Composable
        }

    }

}