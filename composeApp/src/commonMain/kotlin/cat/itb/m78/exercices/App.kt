package cat.itb.m78.exercices


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import cat.itb.m78.exercices.theme.AppTheme
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.generatedFaceTwo
import org.jetbrains.compose.reload.DevelopmentEntryPoint
import org.jetbrains.compose.resources.painterResource

data class Contact(val fullName: String, val email: String, val phone: String)
val contact = Contact("Marta Casserres", "marta@example.com", "934578484")

@Composable
internal fun App() = AppTheme {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(Res.drawable.generatedFaceTwo),
                modifier = Modifier.size(100.dp).clip(CircleShape),
                contentDescription = null
            )
            Text(contact.fullName, fontSize = 35.sp, fontWeight = FontWeight.Bold)
            Box(modifier = Modifier.background(Color.LightGray, RoundedCornerShape(percent = 35)).padding(10.dp)) {
                Column {
                    Text("\uD83D\uDCE7 " + contact.email)
                    Text("\uD83D\uDCDE " + contact.phone)
                }
            }
        }
    }
}
