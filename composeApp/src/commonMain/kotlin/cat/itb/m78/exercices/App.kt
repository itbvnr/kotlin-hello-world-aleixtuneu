package cat.itb.m78.exercices

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.em
import cat.itb.m78.exercices.theme.AppTheme


@Composable
internal fun App() = AppTheme {
    Box(Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize(),
               horizontalAlignment = Alignment.CenterHorizontally,
               verticalArrangement = Arrangement.Center) {
            Text("Welcome!",
                fontSize = 2.em)
            Text("Start learning now")
            Button(onClick = { }) {
                Text("Login",)
            }
            Button(onClick = { }) {
                Text("Register")
            }
        }
    }
}
