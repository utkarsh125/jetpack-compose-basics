package com.example.wishlistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.wishlistapp.data.Wish
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: WishViewModel,
    navController: NavController
) {

    val snackMessage = remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    if(id != 0L){
        val wish = viewModel.getAWishById(id).collectAsState(initial = Wish(0L, "", ""))
        viewModel.wishTitleState = wish.value.title
        viewModel.wishDescriptionState = wish.value.description
    }else{
        viewModel.wishTitleState = ""
        viewModel.wishDescriptionState = ""
    }


    Scaffold (
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = { AppBarView(title =
        if(id != 0L) stringResource(id = R.string.update_wish) else stringResource(id = R.string.add_wish)
        )
        //navigateUp(): return the user where they came from
        {navController.navigateUp()}

        }
    ) {
        Column(
            modifier = Modifier.padding(it).wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.height(10.dp))

            WishTextField(label = "Title", value = viewModel.wishTitleState, onValueChanged = {
                viewModel.onWishTitleChanged(it)
            })

            Spacer(modifier = Modifier.height(10.dp))

            WishTextField(label = "Description", value = viewModel.wishDescriptionState, onValueChanged = {
                viewModel.onWishDescriptionChanged(it)
            })

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.teal_700),
                    contentColor = Color.White
                ),
                onClick = {

                //Make sure the TextFieldStates are not empty, because if they are empty -> an empty wishlist item will be added
                //so add checks accordingly.
                if(viewModel.wishTitleState.isNotEmpty() && viewModel.wishDescriptionState.isNotEmpty()){
                    //TODO:Update wish
                    //TODO: Add Wish

                    if(id != 0L){
                        //TODO: Update wish
                        viewModel.updateWish(
                            Wish(
                                id = id,
                                title = viewModel.wishTitleState.trim(),
                                description = viewModel.wishDescriptionState.trim()
                            )
                        )
                        snackMessage.value = "Wish has been updated"
                    }else{

                        viewModel.addWish(
                            Wish(
                                title = viewModel.wishTitleState.trim(),
                                description = viewModel.wishDescriptionState.trim()
                            )
                        )
                        snackMessage.value = "Wish has been created"
                    }
                }else{
                    // Enter fields for wish to be created
                    snackMessage.value = "Enter fields to create a wish"
                }
                    scope.launch {
                        snackbarHostState.showSnackbar(snackMessage.value)
                        kotlinx.coroutines.delay(1000)
                        navController.navigateUp()
                    }
            }) {
                Text(
                    //if we do have an id of not 0, means that we are currently updating
                    //if it is zero then we are adding a new wish list item.
                    text = if (id != 0L) stringResource(id = R.string.update_wish)
                    else stringResource(
                        id = R.string.add_wish
                    ),
                    style = TextStyle(
                        fontSize = 18.sp
                    )
                )
            }


        }
    }

}

@Composable
fun WishTextField(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit
){
    OutlinedTextField(
        value = value,
        label = { Text(text = label, color = Color.Black) },
        onValueChange = onValueChanged,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(ContextCompat.getColor(LocalContext.current, R.color.black)),
            unfocusedBorderColor = Color(ContextCompat.getColor(LocalContext.current, R.color.black)),
            cursorColor = Color(ContextCompat.getColor(LocalContext.current, R.color.black)),
            focusedLabelColor = Color(ContextCompat.getColor(LocalContext.current, R.color.black)),
            unfocusedLabelColor = Color(ContextCompat.getColor(LocalContext.current, R.color.black))
        )

    )
}

@Preview
@Composable
fun WishTestFieldPrev(){
    WishTextField(label = "text", value = "text", onValueChanged = {})
}