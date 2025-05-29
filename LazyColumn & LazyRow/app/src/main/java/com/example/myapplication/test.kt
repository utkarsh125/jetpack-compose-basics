package com.example.myapplication
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Contact (
    //userName
    //phoneNumber
    val name: String,
    val phoneNumber: String

)


@Composable
fun test() {

    val contacts = remember {
        listOf(
            Contact(
                name = "Utkarsh",
                phoneNumber = "123-456-789"
            ),
            Contact(
                name = "Utkarsh",
                phoneNumber = "123-456-789"
            ),
            Contact(
                name = "Utkarsh",
                phoneNumber = "123-456-789"
            ),
            Contact(
                name = "Utkarsh",
                phoneNumber = "123-456-789"
            ),
            Contact(
                name = "Utkarsh",
                phoneNumber = "123-456-789"
            ),
            Contact(
                name = "Utkarsh",
                phoneNumber = "123-456-789"
            ),
            Contact(
                name = "Utkarsh",
                phoneNumber = "123-456-789"
            ),
            Contact(
                name = "Utkarsh",
                phoneNumber = "123-456-789"
            ),
            Contact(
                name = "Utkarsh",
                phoneNumber = "123-456-789"
            ),
            Contact(
                name = "Utkarsh",
                phoneNumber = "123-456-789"
            ),
            Contact(
                name = "Utkarsh",
                phoneNumber = "123-456-789"
            ),
            Contact(
                name = "Utkarsh",
                phoneNumber = "123-456-789"
            ),
            Contact(
                name = "Utkarsh",
                phoneNumber = "123-456-789"
            ),
            Contact(
                name = "Utkarsh",
                phoneNumber = "123-456-789"
            ),
            Contact(
                name = "Utkarsh",
                phoneNumber = "123-456-789"
            ),
            Contact(
                name = "Utkarsh",
                phoneNumber = "123-456-789"
            ),
            Contact(
                name = "Utkarsh",
                phoneNumber = "123-456-789"
            ),
            Contact(
                name = "Utkarsh",
                phoneNumber = "123-456-789"
            ),
            Contact(
                name = "Utkarsh",
                phoneNumber = "123-456-789"
            ),
            Contact(
                name = "Utkarsh",
                phoneNumber = "123-456-789"
            ),
            Contact(
                name = "Utkarsh",
                phoneNumber = "123-456-789"
            ),
            Contact(
                name = "Utkarsh",
                phoneNumber = "123-456-789"
            ),
            Contact(
                name = "Utkarsh",
                phoneNumber = "123-456-789"
            ),
            Contact(
                name = "Utkarsh",
                phoneNumber = "123-456-789"
            ),
            Contact(
                name = "Utkarsh",
                phoneNumber = "123-456-789"
            ),
            Contact(
                name = "Utkarsh",
                phoneNumber = "123-456-789"
            ),
            Contact(
                name = "Utkarsh",
                phoneNumber = "123-456-789"
            ),
            Contact(
                name = "Utkarsh",
                phoneNumber = "123-456-789"
            ),


        )
    }

    //put the list in a LazyColumn
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(contacts) { contact ->

            Card (
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ){
                Column {
                    Text(text = contact.name)
                    Text(text = contact.phoneNumber)
                }
            }
        }
    }
}

