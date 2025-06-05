package com.example.myrecipeapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//Build connection to this baseUrl
//After connection, convert the incoming JSON into Kotlin Data Classes using GSON
private val retrofit = Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/").addConverterFactory(GsonConverterFactory.create()).build()
val recipeService = retrofit.create(ApiService::class.java) //We want to have to have this service

interface ApiService {

    @GET("categories.php")
    suspend fun getCategories(): CategoriesResponse

    //suspend helps you to run network calls in the background without affecting the UI
    //prevents the UI freezes


}