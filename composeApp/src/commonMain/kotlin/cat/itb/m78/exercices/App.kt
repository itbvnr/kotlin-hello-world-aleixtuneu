package cat.itb.m78.exercices


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.m78.exercices.theme.AppTheme
import org.jetbrains.compose.reload.DevelopmentEntryPoint

// Equip 1 ViewModel
class TeamOneViewModel : ViewModel() {
    var teamOneScore = mutableStateOf(0)
    fun addScoreTeamOne() {
        teamOneScore.value += 1
    }
}

// Equip 2 View Model
class TeamTwoViewModel : ViewModel() {
    var teamTwoScore = mutableStateOf(0)
    fun addScoreTeamTwo() {
        teamTwoScore.value += 1
    }
}

@Composable
internal fun App() = AppTheme {
    Box(Modifier.fillMaxSize()){
        val teamOneViewModel = viewModel { TeamOneViewModel() }
        val teamTwoViewModel = viewModel { TeamTwoViewModel() }

        Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            // Equip 1
            Column(Modifier.border(width = 1.dp, color = Color.Black), verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(teamOneViewModel.teamOneScore.value.toString())

                Button(onClick = {
                    teamOneViewModel.addScoreTeamOne()
                }) {
                    Text("Team One Score")
                }
            }

            Spacer(Modifier.width(20.dp))

            // Equip 2
            Column(Modifier.border(width = 1.dp, color = Color.Black), verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(teamTwoViewModel.teamTwoScore.value.toString())

                Button(onClick = {
                    teamTwoViewModel.addScoreTeamTwo()
                }) {
                    Text("Team Two Score")
                }
            }
        }
    }
}
