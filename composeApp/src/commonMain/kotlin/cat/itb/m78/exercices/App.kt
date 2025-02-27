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
data class Joke(
    val id: Int, val type: String, val setup: String, val punchline: String
)

// 2. Utilitzar ViewModel
class JokesViewModel() : ViewModel() {
    val joke = mutableStateOf<Joke?>(null)

    // 3. Actualitzar l'objecte fent servir la api

    // es llança en un fil a part
    init {
        viewModelScope.launch(Dispatchers.Default) {
            joke.value = JokesApi.list().random()
        }
    }

    fun getAJoke() {
        viewModelScope.launch(Dispatchers.Default) {
            joke.value = JokesApi.list().random()
        }
    }
}

// 4. Classe que fa servir la api
object JokesApi {
    // Atributs
    val url = "https://api.sampleapis.com/jokes/goodJokes"
    val client = HttpClient() {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    // Funcions
    suspend fun list() = client.get(url).body<List<Joke>>()
}

@Composable
fun JokesScreen() {
    val viewModel = viewModel { JokesViewModel() }
    val joke = viewModel.joke.value

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        if (joke != null) {
            Text(joke.setup)
            Text(joke.punchline)

            Button(onClick= {viewModel.getAJoke()}) {
                Text("Get a Joke")
            }
        } else {
            CircularProgressIndicator()
        }
    }
}

@Composable
internal fun App() = AppTheme {
    JokesScreen()
}
