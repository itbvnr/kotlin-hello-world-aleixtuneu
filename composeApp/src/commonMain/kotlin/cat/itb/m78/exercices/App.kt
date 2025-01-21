package cat.itb.m78.exercices

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cat.itb.m78.exercices.theme.AppTheme

@Composable
internal fun App() = AppTheme {
    Box(Modifier.fillMaxSize().border(2.dp, Color.Gray)) {
        Text("Hello World!")
    }
}