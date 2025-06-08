package com.example.wishlistapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wishlistapp.data.Wish
import com.example.wishlistapp.data.WishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

//All viewmodels should inherit from ViewModel()
class WishViewModel(
    //add a param wishRepository
    private val wishRepository: WishRepository = Graph.wishRepository
): ViewModel() {

    //it's taking care of the communication between the data and UI
    var wishTitleState by mutableStateOf("")
    var wishDescriptionState by mutableStateOf("")


    fun onWishTitleChanged(newString: String){
        wishTitleState = newString
    }

    fun onWishDescriptionChanged(newString: String){
        wishDescriptionState = newString
    }

    //lateinit: lateinit is a modifier used in Kotlin to declare a non-null variable
    // that will be initialized later, after its declaration. before we actually use it
    lateinit var getAllWishes: Flow<List<Wish>>

    //initializer
    init {
        viewModelScope.launch {
            getAllWishes = wishRepository.getWishes()
        }
    }

    fun addWish(wish: Wish){
        viewModelScope.launch (Dispatchers.IO){
            //Dispatchers are responsible for deciding what thread or threads the coroutine will work on
            //Input to add a wish
            //output to load a wish
            //maintains a shared pool of threads that can shrink or grow on demand
            //it is used to ensure system stability and prevent the blocking of the main thread
            //in simple words, it increases efficiency
            wishRepository.addAWish(wish = wish)
        }
    }

    fun getAWishById(id: Long): Flow<Wish>{
        return wishRepository.getAWishById(id)
    }

    fun updateWish(wish: Wish){
        viewModelScope.launch (Dispatchers.IO){
            wishRepository.updateAWish(wish = wish)
        }
    }

    fun deleteWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO){
            wishRepository.deleteAWish(wish = wish)
        }
    }

}