package com.example.wishlistapp

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.wishlistapp.data.DummyWish
import com.example.wishlistapp.data.Wish





@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeView(navController: NavHostController) {

    val context = LocalContext.current
    val viewModel: WishViewModel = viewModel()

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
                    navController.navigate(Screen.AddScreen.route + "/0L")

                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }



    ) { innerPadding ->

        val wishlist = viewModel.getAllWishes.collectAsState(initial = listOf())
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // list of items will go here

            items(wishlist.value, key={wish -> wish.id}){wish->

                val dismissState = rememberDismissState(
                    confirmStateChange = {
                        if(it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart){
                            viewModel.deleteWish(wish)
                        }
                        true
                    }
                )

                SwipeToDismiss(
                    state = dismissState,
                    background = {},
                    directions = setOf(DismissDirection.StartToEnd, DismissDirection.EndToStart),
                    dismissThresholds = {
                        FractionalThreshold(0.25f)
                    },
                    dismissContent = {
                        WishItem(wish = wish) {

                            val id = wish.id
                            navController.navigate(Screen.AddScreen.route + "/$id")
                        }
                    }
                )
            }

            /*items(DummyWish.wishList){
                wish -> WishItem(wish = wish) { }
            }*/
        }
    }
}

@Composable
fun WishItem(wish: Wish, onClick: ()-> Unit){

    val myCustomColor = Color(0xFF018786)
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