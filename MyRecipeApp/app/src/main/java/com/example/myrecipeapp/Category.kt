package com.example.myrecipeapp

data class Category (
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
)

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