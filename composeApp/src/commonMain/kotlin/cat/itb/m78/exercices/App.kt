package cat.itb.m78.exercices

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.m78.exercices.theme.AppTheme
import coil3.compose.AsyncImage
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults


// https://api.sampleapis.com/countries/countries

// 1. Model de dades
@Serializable
data class Country(
    val abbreviation: String,
    val capital: String,
    val currency: String,
    val name: String,
    val phone: String,
    val population: Int? = null,
    val media: Media,
    val id: Int
)

@Serializable
data class Media(
    val flag: String,
    val emblem: String,
    val orthographic: String
)


// 2. Utilitzar ViewModel
class CountriesViewModel() : ViewModel() {
    val countries = mutableStateOf<List<Country>>(emptyList())

    // 3. Actualitzar l'objecte fent servir la api

    // es llança un fil a part
    init {
        viewModelScope.launch(Dispatchers.Default) {
            try {
                val allCountries = CountriesApi.list()
                countries.value = allCountries
            } catch (e: Exception) {
                println("Error al obtrenir les dades: ${e.message}")
                countries.value = emptyList()
            }
        }
    }
}

// 4. Clase que fa servir la api
object CountriesApi {
    // Atributos
    val url = "https://api.sampleapis.com/countries/countries"
    val client = HttpClient() {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    // Funciones
    suspend fun list() = client.get(url).body<List<Country>>()
}

@Composable
fun CountriesScreen() {
    val viewModel = viewModel { CountriesViewModel() }
    val countries = viewModel.countries.value

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (countries.isEmpty()) {
            CircularProgressIndicator(modifier = Modifier)
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(countries) { country ->
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
                            Text(text = "${country.name}", style = MaterialTheme.typography.bodyLarge)

                            // Capital
                            Text(text = "${country.capital}", style = MaterialTheme.typography.bodyMedium)

                            // Bandera
                            AsyncImage(
                                model = country.media.flag,
                                contentDescription = "${country.name}", // Sempre és bo tenir una descripció accessible
                                modifier = Modifier.size(100.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
internal fun App() = AppTheme {
    CountriesScreen()
}