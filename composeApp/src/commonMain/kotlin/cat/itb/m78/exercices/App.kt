package cat.itb.m78.exercices

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cat.itb.m78.exercices.theme.AppTheme
import com.russhwolf.settings.Settings
import kotlinx.serialization.Serializable
import com.russhwolf.settings.set

// Iniciar Settings
val settings: Settings = Settings()

@Composable
internal fun App() = AppTheme {
    // Obtenir el contador (si no existeix, se'n posa un valor per defecte de 0)
    var openCount = settings.getIntOrNull("open_count") ?: 0

    // Incrementar el contador cada vegada que s'obre la app
    LaunchedEffect(key1 = Unit) {
        openCount += 1
        settings["open_count"] = openCount
    }

    Column {
        Text(text = "Aquesta app s'ha obert $openCount vegades.")
    }
}
