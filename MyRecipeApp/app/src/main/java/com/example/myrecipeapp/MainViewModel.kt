package com.example.myrecipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    //we can also have data classes as mutableStates
    private val _categoryState = mutableStateOf(RecipeState())
    val categoriesState: State<RecipeState> = _categoryState

    //the moment my MainViewModel is used,
    //fetch the categories
    init {
        fetchCategories()
    }

    private fun fetchCategories(){
        viewModelScope.launch {
            //run our code here (Co-routines, runs in the background, not on the main thread)
            try{
                val response = recipeService.getCategories()
                _categoryState.value = _categoryState.value.copy(
                    list = response.categories,
                    loading = false,
                    error = null
                )
            }catch (e: Exception){
                _categoryState.value = _categoryState.value.copy(
                    loading = false,
                    error = "Error fetching categories ${e.message}"
                )
            }
        }
    }

    data class RecipeState (
        val loading: Boolean = true,
        val list: List<Category> = emptyList(),
        val error: String? = null //when we do not have error it would be null


    )
}