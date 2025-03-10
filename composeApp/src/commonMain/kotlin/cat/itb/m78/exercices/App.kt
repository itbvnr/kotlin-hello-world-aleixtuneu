package cat.itb.m78.exercices

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import cat.itb.m78.exercices.theme.AppTheme
import com.russhwolf.settings.Settings
import kotlinx.serialization.Serializable
import com.russhwolf.settings.set

// Iniciar Settings
val settings: Settings = Settings()

@Composable
internal fun App() = AppTheme {
    val savedName = settings.getStringOrNull("user_name") ?: ""  // Recupera el nom des de Settings
    val (name, setName) = remember { mutableStateOf(savedName) }
    val (isNameSaved, setIsNameSaved) = remember { mutableStateOf(false) }  // Estat per controlar si el nom s'ha guardat

    Column(modifier = Modifier.padding(16.dp)) {
        // Mostrar el el nom si existeix
        Text(text = "Hola $name!")


        // Escriure el nom
        TextField(
            value = name,
            onValueChange = { newValue -> setName(newValue) },  // Actualitza el nom
            modifier = Modifier.padding(top = 8.dp)
        )

        // Botó per guardar el nom
        Button(
            onClick = {
                settings["user_name"] = name // Guardar el nom a Settings
                setIsNameSaved(true)  // Guardar el nom i actualitzar el text
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(text = "Save")
        }
    }
}
