package com.example.wishlistapp

import androidx.room.Room
import com.example.wishlistapp.data.WishDatabase
import com.example.wishlistapp.data.WishRepository
import android.content.Context



//object is used to define Singleton,
// meaning that it will have only one instance will exist in the application
// we cannot create another graph object of the same name


//It is being utilized a service locator that will provide dependency to the rest of the app
object Graph {

    //lateinit is used to declare a non-nullable property database of type WishDatabase
    //that is being promised to be initialized before being processed

    lateinit var database: WishDatabase

    val wishRepository by lazy {
        //the lazy keyword here means that the initialization code inside of the curly braces
        //will be executed only once first time when wish repository is accessed
        WishRepository(wishDao = database.wishDao())
    }


    //This is a method to provide the necessary context for initializing the wish database
    fun provide(context: Context){

        database = Room.databaseBuilder(context, WishDatabase::class.java, "wishlist").build()
    }
}