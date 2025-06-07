package com.example.wishlistapp

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.wishlistapp.data.DummyWish
import com.example.wishlistapp.data.Wish


@Composable
fun HomeView(navController: NavHostController) {

    val context = LocalContext.current

    Scaffold (
        topBar = {
            AppBarView(
                title = "Wishlist", {
                    Toast.makeText(
                        context,
                        "Button Clicked",
                        Toast.LENGTH_LONG
                    ).show()
                }
            )


        },

        floatingActionButton = {
            FloatingActionButton(
                contentColor = Color.White,
                containerColor = Color.Black,
                onClick = {

                    Toast.makeText(context, "FAButton Clicked.", Toast.LENGTH_LONG).show()
                    navController.navigate(Screen.AddScreen.route)

                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }



    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // list of items will go here
            items(DummyWish.wishList){
                wish -> WishItem(wish = wish) { }
            }
        }
    }
}

@Composable
fun WishItem(wish: Wish, onClick: ()-> Unit){

    val myCustomColor = Color(0xFFBCFFDB)
    Card (
        modifier = Modifier.fillMaxWidth().padding(top = 8.dp, start = 8.dp, end = 8.dp).clickable {
            onClick() //this is our own onClick function (defined in the arguments of this function)
        },
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        colors = CardDefaults.cardColors(containerColor = myCustomColor)
    ) {

        Column (
            modifier = Modifier.padding(16.dp)
        ){

            Text(text = wish.title, fontWeight = FontWeight.ExtraBold)
            Text(text = wish.description)


        }

    }
}