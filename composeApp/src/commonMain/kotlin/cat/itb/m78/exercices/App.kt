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
    var productName by remember { mutableStateOf("") }
    var productAmount by remember { mutableStateOf("") }
    var productsList by remember { mutableStateOf(mutableMapOf<String, Int>()) }

    Box(Modifier.fillMaxSize()){
        // Afegir productes
        Column(modifier = Modifier.fillMaxSize()) {
            // Nom del producte
            TextField(
                value = productName,
                label = { Text("Nom") },
                onValueChange = { productName = it }
            )

            // Quantitat del producte
            TextField(
                value = productAmount,
                label = { Text("Quantitat") },
                onValueChange = { productAmount = it }
            )

            // Botó afegir
            Button(onClick = {
                val amount = productAmount.toIntOrNull()
                if (productName.isNotBlank() && amount != null) {
                    productsList = productsList.toMutableMap().apply {
                        this[productName] = amount
                    }
                }
            }) {
                Text("Afegir")
            }

            // Mostrar llista de productes
            Column {
                productsList.forEach { (name, amount) ->
                    Text("$name: $amount unitats")
                }
            }
        }
    }
}