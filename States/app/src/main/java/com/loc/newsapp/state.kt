import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun state(modifier: Modifier = Modifier) {


    var count by remember { mutableStateOf(0) } //remember the count even after UI changes
    //such that the UI retains the changed information.


    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Count: $count", fontSize = 24.sp)
        Button(
            onClick = {
                count++
            }
        ) {
            Text("Increment")
        }
    }
}