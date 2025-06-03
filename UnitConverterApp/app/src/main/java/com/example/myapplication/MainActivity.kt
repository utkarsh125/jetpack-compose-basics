package com.example.myapplication

import android.os.Bundle
import android.widget.Space
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    UnitConverter()
                }
            }
        }
    }
}

/*@Preview(showBackground = true, showSystemUi = true)*/
@Composable
fun UnitConverter() {

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("")}
    var inputUnit by remember { mutableStateOf("Centimeters") } //default: cm
    var outputUnit by remember { mutableStateOf("Meters")}//default: m
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableStateOf(1.00) }
    val oConversionFactor = remember { mutableStateOf(1.00) }


    //Conversion Logic
    fun convertUnits(){

        //Elvis Operator -> ?:
        val inputValueDouble = inputValue.toDoubleOrNull()?: 0.0 //Double value is null then make it a double

        val result = (inputValueDouble * conversionFactor.value * 100.0 / oConversionFactor.value).roundToInt() / 100.0
        outputValue = result.toString()

    }


    Column(
        modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        //Here all the UI elements will be stacked below each other
        Text("Unit Converter", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(16.dp))
        //All of them stacked next to each other
        OutlinedTextField(value = inputValue, onValueChange = {
            //here goes what should happen when Value of OutlineTextField changes
            inputValue = it


        }, label = { Text("Enter value")})

        Row {

            Box {
                Button(onClick = {
                    iExpanded=true

                }) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }

                DropdownMenu(
                    expanded = iExpanded, //by default it is close
                    onDismissRequest = {
                        //callback when the menu is dismissed
                        iExpanded=false
                    }
                ) {
                    DropdownMenuItem(text = {
                        Text("Centimeters")
                    }, onClick = {
                        iExpanded = false
                        inputUnit = "Centimeters"
                        conversionFactor.value = 0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = {
                        Text("Meters")
                    }, onClick = {
                        iExpanded = false
                        inputUnit = "Meters"
                        conversionFactor.value = 1.0 //meter is the base unit
                        convertUnits()
                    })
                    DropdownMenuItem(text = {
                        Text("Milimeters")
                    }, onClick = {
                        iExpanded = false
                        inputUnit = "Milimeters"
                        conversionFactor.value = 0.001
                        convertUnits()
                    })
                    DropdownMenuItem(text = {

                        Text("Kilometers")
                    }, onClick = {
                        iExpanded = false
                        inputUnit = "Kilometers"
                        conversionFactor.value = 1000.0
                        convertUnits()

                    })
                }

            }

            Spacer(modifier = Modifier.width(16.dp))

            //OUTPUT BOX
            Box {

                Button(onClick = {
                    oExpanded = true
                }) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }

                DropdownMenu(
                    expanded = oExpanded, //by default it is close
                    onDismissRequest = {
                        //callback when the menu is dismissed
                        oExpanded=false

                    }
                ) {
                    DropdownMenuItem(text = {
                        Text("Centimeters")
                    }, onClick = {
                        oExpanded=false
                        outputUnit = "Centimeters"
                        oConversionFactor.value = 0.001
                        convertUnits()
                    })
                    DropdownMenuItem(text = {
                        Text("Meters")
                    }, onClick = {
                        oExpanded=false
                        outputUnit = "Meters"
                        oConversionFactor.value = 1.00
                        convertUnits()

                    })
                    DropdownMenuItem(text = {
                        Text("Milimeters")
                    }, onClick = {
                        oExpanded=false
                        outputUnit = "Milimeters"
                        oConversionFactor.value = 0.001
                        convertUnits()

                    })
                    DropdownMenuItem(text = {
                        Text("Kilometers")
                    }, onClick = {
                        oExpanded = false
                        inputUnit = "Kilometers"
                        oConversionFactor.value = 1000.0 // ✅ add this line
                        convertUnits()
                    })
                }
            }


        }

        //Result Text
        /*TODO: Font change*/

        Text("Result: $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineMedium
            )

    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}