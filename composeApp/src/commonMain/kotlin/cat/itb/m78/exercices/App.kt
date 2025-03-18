package cat.itb.m78.exercices

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import cat.itb.m78.exercices.theme.AppTheme
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.ContentType.Application.Json
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.jetbrains.compose.reload.DevelopmentEntryPoint

// Definció de destins
object Destination {
    @Serializable
    data object EmbassamentsScreen
    @Serializable
    data class EmbassamentInfoScreen(val embassamentId: String)
}

// https://analisi.transparenciacatalunya.cat/resource/gn9e-3qhr.json

// 1. Model de dades
@Serializable
data class Embassament(
    val dia: String,
    val estaci: String,
    val nivell_absolut: Double,
    val percentatge_volum_embassat: Double,
    val volum_embassat: Double
)

// 2. Utilitzar ViewModel
class EmbassamentsViewModel() : ViewModel() {
    val embassaments = mutableStateOf<List<Embassament>>(emptyList())

    // 3. Actualitzar l'objecte fent servir la api
    init {
        viewModelScope.launch(Dispatchers.Default) {
            try {
                val totsEmbassaments = EmbassamentsApi.list()
                embassaments.value = totsEmbassaments
            } catch (e: Exception) {
                println("Error al obtenir les dades: ${e.message}")
                embassaments.value = emptyList()
            }
        }
    }
}

// 4. Classe que fa servir la api
object EmbassamentsApi {
    // Atributs
    val url = "https://analisi.transparenciacatalunya.cat/resource/gn9e-3qhr.json";
    val client = HttpClient() {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    // Funcions
    suspend fun list() = client.get(url).body<List<Embassament>>()
}

// Pantalla 1
@Composable
fun EmbassamentsScreen(navigateToEmbassamentInfoScreen: (String) -> Unit) {
    val viewModel = viewModel { EmbassamentsViewModel() }
    val embassaments = viewModel.embassaments.value

    val embassamentsDistinct = embassaments.distinctBy { it.estaci }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (embassaments.isEmpty()) {
            CircularProgressIndicator(modifier = Modifier)
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(embassamentsDistinct) { embassament ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            // Nom
                            ClickableText(
                                text = AnnotatedString("${embassament.estaci}"),
                                onClick = { navigateToEmbassamentInfoScreen(embassament.estaci) }
                            )
                        }
                    }
                }
            }
        }
    }
}

// Pantalla 2
@Composable
fun EmbassamentInfoScreen(embassamentId: String) {
    val viewModel = viewModel { EmbassamentsViewModel() }
    val embassaments = viewModel.embassaments.value

    val embassamentsInfo = embassaments.filter { it.estaci == embassamentId }

    if (embassamentsInfo != null) {
        LazyColumn(
            modifier = Modifier.padding(16.dp).fillMaxWidth()
        ) {
            items(embassamentsInfo) { embassamentInfo ->
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    // Dia
                    Text( text = "Dia: ${embassamentInfo.dia}")

                    // Nom
                    // Text( text = "Nom: ${embassamentInfo.estaci}")

                    // Nivell absolut
                    Text( text = "Nivell Absolut: ${embassamentInfo.nivell_absolut}")

                    // Percentatge volum embassat
                    Text( text = "Percentatge de Volum Embassat: ${embassamentInfo.percentatge_volum_embassat}")

                    // Volum embassat
                    Text( text = "Volum Embassat: ${embassamentInfo.volum_embassat}")
                }
            }

        }
    } else {
        Text("Embassament no trobat.")
    }


}

@Composable
internal fun App() = AppTheme {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destination.EmbassamentsScreen) {
        composable<Destination.EmbassamentsScreen> {
            EmbassamentsScreen { id ->
                navController.navigate(Destination.EmbassamentInfoScreen(id))
            }
        }
        composable<Destination.EmbassamentInfoScreen> { backStack ->
            val embassamentId = backStack.arguments?.getString("embassamentId") ?: ""
            EmbassamentInfoScreen(embassamentId)
        }
    }
}
