package cat.itb.m78.exercices


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.approachLayout
import androidx.compose.ui.unit.dp
import cat.itb.m78.exercices.theme.AppTheme
import co.touchlab.kermit.Message
import org.jetbrains.compose.reload.DevelopmentEntryPoint

@Composable
internal fun App() = AppTheme {
    data class Message(val author: String, val body: String)

    val names = listOf(
        "Ellison Curry",
        "Briggs Willis",
        "Alexa Murphy",
        "Cameron Berry",
        "Annabelle Villarreal",
        "Nikolai Wiley",
        "Lauryn Morrow",
        "Kyree Hardy",
        "Jessica Lang",
        "Wells Wilson",
        "Luna Foster",
        "Kayden Taylor",
        "Sofia Mann",
        "Nehemiah Randall",
        "Christina Gordon",
        "Karter Kramer",
        "Hanna Morales",
        "Aaron Velez",
        "Megan Delarosa",
        "Osiris Johnson",
        "Emma Atkins",
        "Cason McKee",
        "Kori Walls",
        "Larry Shepherd",
    )
    val body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ac vestibulum nunc."
    val messages = List(100){
        Message(names.random(), body)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()
        ) {
            items(messages) { message ->
                Column(modifier = Modifier.padding(10.dp)) {
                    Row(modifier = Modifier.background(Color.LightGray, RoundedCornerShape(percent = 10)).padding(20.dp).fillMaxWidth()
                    ) {
                        Text(message.author + "\n" + message.body)
                    }
                }
            }
        }

        Button(onClick = { },
            modifier = Modifier.padding(10.dp).align(Alignment.BottomEnd).width(60.dp).height(60.dp)
        ) {
            Text("+")
        }
    }
}
