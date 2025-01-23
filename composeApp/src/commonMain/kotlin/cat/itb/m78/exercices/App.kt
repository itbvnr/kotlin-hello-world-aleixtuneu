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
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        var numberInput by remember { mutableStateOf("") }
        var result by remember { mutableStateOf("Introdueix un número") }

        val secretNumber = remember { (0..100).random() }
        var intents by remember { mutableStateOf(0) };

        println(secretNumber)

        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            TextField(
                value = numberInput,
                label = { Text("Endevina el número secret") },
                onValueChange = { numberInput = it }
            )

            Button(onClick = {
                if (numberInput.toIntOrNull() != null) {
                    intents++
                    if ((numberInput.toInt()) == secretNumber) {
                        result = "Has endevinat el número secret!"
                    } else {
                        if (numberInput.toInt() < secretNumber) {
                            result = "El número secret és mes gran"
                        } else if (numberInput.toInt() > secretNumber) {
                            result = "El número secret és més petit"
                        }
                    }
                } else {
                    intents++
                    result = "Introdueix un valor vàlid"
                }
            }) {
                Text("Validar")
            }

            Text("Intents $intents")

            Text(result)
        }
    }
}
