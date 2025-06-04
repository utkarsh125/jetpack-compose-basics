import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


data class ShoppingItem(
    val id: Int,
    var name: String,
    var quantity: Int,
    var isEditing: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShoppingListApp(){

    //sItems is going to maintain the list of items that we have

    var sItems by remember { mutableStateOf(listOf<ShoppingItem>()) }
    var showDialog by remember { mutableStateOf(true) }
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                showDialog = true
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Add Item")
        }

        LazyColumn(
            //indefinite amount of columns, but render only that are required
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(sItems){
                /*ShoppingListItem(it, {}, {})*/

                item ->
                if(item.isEditing){
                    ShoppingItemEditor(item = item, onEditComplete = {
                        editedName, editedQuantity ->
                        sItems = sItems.map { it.copy(isEditing = false) }
                        val editedItem = sItems.find { it.id == item.id }

                        editedItem?.let {
                            it.name = editedName
                            it.quantity = editedQuantity
                        }
                    })

                }else{
                    ShoppingListItem(item = item, onEditClick = {
                        sItems = sItems.map{ it.copy(
                            isEditing = it.id == item.id //will return true or false (to find out on which item we have clicked on
                        )}
                    }, onDeleteClick = {
                        sItems = sItems-item
                    })
                }
            }
        }
    }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = {
                        //if that item is not empty then
                        if(itemName.isNotBlank()){
                            //Create a new shopping item
                            //then push it to the shopping item list

                            val newItem = ShoppingItem(
                                id = sItems.size+1,
                                name = itemName,
                                quantity = itemQuantity.toInt()
                            )

                            //add newItem to the list
                            sItems = sItems + newItem
                            showDialog = false
                            itemName = "" //reset after add
                        }
                    }) {
                        Text("Add")
                    }

                    //CANCEL BUTTON
                    Button(onClick = {
                        showDialog = false
                    }) {
                        Text("Cancel")
                    }

                }
            },
            /*dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            },*/
            title = {
                Text("Add Shopping Item")
            },
            text = {
                Column {
                    OutlinedTextField(
                        value = itemName,
                        onValueChange = {
                            itemName = it
                        },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )

                    OutlinedTextField(
                        value = itemQuantity,
                        onValueChange = {
                            itemQuantity = it
                        },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )


                }
            }
        )
    }

}

@Composable
fun ShoppingItemEditor(item: ShoppingItem, onEditComplete: (String, Int) -> Unit){

    var editedName by remember { mutableStateOf(item.name) } //we will use this as the override for the item name
    var editedQuantity by remember { mutableStateOf(item.quantity.toString()) }//we will use this as the override for the quantity of the item
    var isEditing by remember { mutableStateOf(item.isEditing) } //check default value of isEditing

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ){
        Column {
            BasicTextField(
                value = editedName,
                onValueChange = { editedName = it },
                singleLine = true,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp)//only take as much as space as the items that are inside it.
                //if the text 5 chars wide this will be only 5 chars wide.
            )

            BasicTextField(
                value = editedQuantity,
                onValueChange = { editedQuantity = it },
                singleLine = true,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp)//only take as much as space as the items that are inside it.
                //if the text 5 chars wide this will be only 5 chars wide.
            )
        }

        Button(
            onClick = {
                isEditing = false
                onEditComplete( editedName, editedQuantity.toIntOrNull() ?: 1)

            }
        ) {
            Text("Save")
        }

    }


}


@Composable
fun ShoppingListItem (
    item: ShoppingItem,
    onEditClick: () -> Unit, //a lambda function has no input/output but will execute
    onDeleteClick: () -> Unit,
){

    Row (
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(
                border = BorderStroke(2.dp, Color(0XFF018786)),
                shape = RoundedCornerShape(20)

            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(item.name, modifier = Modifier.padding(8.dp))
        Text("Qty: ${item.quantity}", modifier = Modifier.padding(8.dp))

        Row(modifier = Modifier.padding(8.dp)){
            IconButton(onClick = onEditClick) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = null)

            }

            IconButton(onClick = onDeleteClick) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)

            }
        }
    }
}