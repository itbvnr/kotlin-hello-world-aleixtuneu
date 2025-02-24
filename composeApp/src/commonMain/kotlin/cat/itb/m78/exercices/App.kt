package cat.itb.m78.exercices


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.m78.exercices.theme.AppTheme
import org.jetbrains.compose.reload.DevelopmentEntryPoint

// ViewModel
class ProductViewModel : ViewModel() {
    var productsList by mutableStateOf(mutableMapOf<String, Int>())
        private set

    fun addProduct(name: String, amount: Int) {
        productsList = productsList.toMutableMap().apply {
            this[name] = amount
        }
    }
}

@Composable
fun App(viewModel: ProductViewModel = viewModel()) {
    var productName by remember { mutableStateOf("") }
    var productAmount by remember { mutableStateOf("") }
    val productsList = viewModel.productsList

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        // Nombre del producto
        TextField(
            value = productName,
            label = { Text("Nom") },
            onValueChange = { productName = it }
        )

        // Cantidad del producto
        TextField(
            value = productAmount,
            label = { Text("Quantitat") },
            onValueChange = { productAmount = it }
        )

        // Botón para agregar producto
        Button(onClick = {
            val amount = productAmount.toIntOrNull()
            if (productName.isNotBlank() && amount != null) {
                viewModel.addProduct(productName, amount)
                productName = ""  // Limpiar campo
                productAmount = "" // Limpiar campo
            }
        }) {
            Text("Afegir")
        }

        // Mostrar la lista de productos
        Column(modifier = Modifier.padding(top = 16.dp)) {
            productsList.forEach { (name, amount) ->
                Text("$name: $amount unitats")
            }
        }
    }
}