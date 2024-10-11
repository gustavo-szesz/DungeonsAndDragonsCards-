import com.ded.Character // Ensure this import is correct
import com.ded.ui.theme.DeDCardsTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ded.FirstScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var currentScreen by remember { mutableStateOf<Screen>(Screen.First) }
            var character by remember { mutableStateOf<com.ded.Character?>(null) }

            DeDCardsTheme {
                when (currentScreen) {
                    Screen.First -> FirstScreen(Modifier) { selectedCharacter ->
                        character = selectedCharacter
                        currentScreen = Screen.AddAttributes
                    }
                    Screen.AddAttributes -> character?.let {
                        AddAttributesScreen(it)
                    }

                    else -> {}
                }
            }
        }
    }
}

sealed class Screen {
    object First : Screen()
    object AddAttributes : Screen()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DeDCardsTheme() {
    }
}