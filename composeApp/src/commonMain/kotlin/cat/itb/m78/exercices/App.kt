package cat.itb.m78.exercices

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.m78.exercices.theme.AppTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

/// 1. Model de dades
@Serializable
data class Tree(
    val codi: String
)

// 2. Utilitzar ViewModel
class TreesViewModel : ViewModel() {
    var totalTrees = mutableStateOf<Int?>(0)

    // 3. Actualitzar l'objecte fent servir la api
    fun countTrees() {
        viewModelScope.launch(Dispatchers.Default) {
            try {
                val trees = TreesApi.list()
                totalTrees.value = trees.size
            } catch (e: Exception) {
                totalTrees.value = -1
                println("Error: ${e.message}")
            }
        }
    }
}

// 4. Classe que fa servir la api
object TreesApi {
    // Atributs
    val url = "https://fp.mateuyabar.com/DAM-M03/UF3/exercicis/files/OD_Arbrat_Zona_BCN.json"
    val client = HttpClient() {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    // Funcions
    suspend fun list() = client.get(url).body<List<Tree>>()
}

@Composable
fun TreesScreen() {
    val viewModel = viewModel { TreesViewModel() }
    val totalTrees = viewModel.totalTrees.value

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

        Button(onClick= {viewModel.countTrees()}) {
            Text("Contar arbres totals")
        }

        Text("Arbres Totals: $totalTrees")
    }
}

@Composable
internal fun App() = AppTheme {
    TreesScreen()
}