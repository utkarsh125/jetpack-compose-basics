package com.example.myrecipeapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category (
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String) : Parcelable


data class CategoriesResponse(
    //we are going to receive a list as a response, with
    //the data class Category (see Category data class)
    val categories: List<Category>
)


/*Got this shit from MealDB*/
/*    "idCategory": "1",
"strCategory": "Beef",
"strCategoryThumb": "https://www.themealdb.com/images/category/beef.png",
"strCategoryDescription"*/