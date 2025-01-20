package cat.itb.m78.exercices


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cat.itb.m78.exercices.theme.AppTheme
import org.jetbrains.compose.reload.DevelopmentEntryPoint

@Composable
internal fun App() = AppTheme {
    Box(modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.Center) {
        var text by remember { mutableStateOf("") }
        var showDialog by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            TextField(
                value = text,
                label = { Text("Name") },
                onValueChange = { text = it }
            )

            Button(onClick = { showDialog = true }) {
                Text("SayHello")
            }

            // Si showDialog és true, mostrar el AlertDialog
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    text = { Text("Hola $text") },
                    confirmButton = {}
                )
            }
        }
    }
}
