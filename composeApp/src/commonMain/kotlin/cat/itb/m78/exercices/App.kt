package cat.itb.m78.exercices


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cat.itb.m78.exercices.theme.AppTheme
import org.jetbrains.compose.reload.DevelopmentEntryPoint

@Composable
internal fun App() = AppTheme {
    Box(Modifier.fillMaxSize()){
        Column(modifier = Modifier.fillMaxSize()){
            var text = remember { mutableStateOf("Good ?!") }

            Text(text.value)

            Button(onClick = {
                text.value = "Good Morning!"
            }) {
                Text("Good Morning!")
            }

            Button(onClick = {
                text.value = "Good Night!"
            }) {
                Text("Good Night!")
            }
        }
    }
}
