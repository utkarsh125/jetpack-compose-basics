package com.example.counterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.counterapp.ui.theme.CounterAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CounterAppTheme {
                Surface {
                    // ✅ Use viewModel() to retain state across rotation
                    val counterViewModel: CounterViewModel = viewModel()
                    ThatCounterApp(viewModel = counterViewModel)
                }
            }
        }
    }
}

@Composable
fun ThatCounterApp(viewModel: CounterViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { viewModel.increment() }) {
                Text("Increment")
            }

            Spacer(modifier = Modifier.width(8.dp)) // Use width for horizontal gap

            Button(onClick = { viewModel.decrement() }) {
                Text("Decrement")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Current Count: ${viewModel.count.value}")
    }
}
