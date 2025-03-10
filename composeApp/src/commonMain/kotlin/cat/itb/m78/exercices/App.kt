package cat.itb.m78.exercices

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.m78.exercices.theme.AppTheme
import com.russhwolf.settings.Settings


private const val KEY ="NAME_K"
object MyNameStorage{
    val settings = Settings()
    fun getName() : String? = settings.getStringOrNull(KEY)
    fun store(name: String){
        settings.putString(KEY, name)
    }
}

class RememberMeViewModel : ViewModel(){
    val myDataStorage = MyNameStorage
    val storedData = mutableStateOf(myDataStorage.getName())
    val nameField = mutableStateOf(myDataStorage.getName()?:"")

    fun updateNameField(name: String){
        nameField.value = name
    }

    fun store(){
        myDataStorage.store(nameField.value)
        storedData.value = myDataStorage.getName()
    }
}

@Composable
fun RememberMyNameScreen(){
    val viewModel = viewModel { RememberMeViewModel() }
    RememberMyNameScreen(viewModel.storedData.value, viewModel.nameField.value, viewModel::updateNameField, viewModel::store)
}

@Composable
fun RememberMyNameScreen(myData: String?, name: String, updateName: (String) -> Unit, save: ()->Unit) {
    Column {
        if(myData!=null){
            Row{
                Text("Hello " )
                Text(myData, fontWeight = FontWeight.Bold)

            }

        }
        OutlinedTextField(name, updateName)
        Button(save){
            Text("Save")
        }
    }
}

@Composable
internal fun App() = AppTheme {
    RememberMyNameScreen()
}
