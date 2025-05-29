package com.example.jetpackcompose

import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    test()
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun test() {


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        //CARD Element.
        Card(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
        ){
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Join the waitlist ", fontSize = 23.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.SansSerif )

                //For spacing, either give Spacer function or use padding

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Image(painter = painterResource(
                    id = R.drawable.logo),
                    contentDescription = "gears",
                    modifier = Modifier.size(100.dp)
                )

                Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)){
                    Button(
                        onClick = { /*TODO*/},
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "Share")

                    }

                    Button(
                        onClick = { /*TODO*/},
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "Newsletter")

                    }
                }
            }
        }
    }



}


