import Raca.Raca
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ded.Character
import com.ded.CharacterRepository
import com.ded.atributos.DistribuicaoPontosPadrao
import com.ded.atributos.DistribuicaoPontosStrategy
import com.ded.classes.ClassePersonagem
import com.ded.ui.theme.DeDCardsTheme
import AtributosPersonagens


@Composable
fun AddAttributesScreen(character: Character) {
    var pontosRestantes by remember { mutableStateOf(27) }
    var snackbarMessage by remember { mutableStateOf("") }
    var snackbarVisible by remember { mutableStateOf(false) }
    val atributos = remember { mutableStateOf(AtributosPersonagens()) }
    val distribuicaoPontosStrategy = DistribuicaoPontosPadrao()

    fun showMessage(message: String) {
        snackbarMessage = message
        snackbarVisible = true
    }

    fun updatePontos(diferenca: Int) {
        pontosRestantes -= diferenca
    }

    fun saveCharacter() {
        val newCharacter = Character(
            name = character.name,
            race = character.race,
            characterClass = character.characterClass,
            atributos = atributos.value // Save updated attributes
        )
        CharacterRepository.saveCharacter(newCharacter)
        showMessage("Personagem salvo com sucesso!")
    }

    fun viewCharacter() {
        val savedCharacter = CharacterRepository.getCharacter()
        if (savedCharacter != null) {
            val atributosString = savedCharacter.atributos.toString(savedCharacter.race)
            showMessage("Personagem: ${savedCharacter.name}, Raça: ${savedCharacter.race}, Classe: ${savedCharacter.characterClass}, Atributos: $atributosString")
        } else {
            showMessage("Nenhum personagem salvo.")
        }
    }

    fun onAtributoChange(label: String, value: Int) {
        atributos.value = when (label) {
            "Força" -> atributos.value.copy(forca = value)
            "Destreza" -> atributos.value.copy(destreza = value)
            "Constituição" -> atributos.value.copy(constituicao = value)
            "Sabedoria" -> atributos.value.copy(sabedoria = value)
            "Inteligência" -> atributos.value.copy(inteligencia = value)
            "Carisma" -> atributos.value.copy(carisma = value)
            else -> atributos.value
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Pontos Restantes: $pontosRestantes", style = TextStyle(fontSize = 20.sp))
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Nome", modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
            Text("Atributo", modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
            Text("Bonus Racial", modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
            Text("Modificador", modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
        }

        Spacer(modifier = Modifier.height(8.dp))

        val atributosList = listOf("Força", "Destreza", "Constituição", "Sabedoria", "Inteligência", "Carisma")
        atributosList.forEach { atributo ->
            AtributoInputRow(
                label = atributo,
                atributos = atributos.value,
                race = character.race,
                updatePontos = ::updatePontos,
                onError = { showMessage(it) },
                distribuicaoPontosStrategy = distribuicaoPontosStrategy,
                onAtributoChange = ::onAtributoChange // Pass the new handler
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        if (snackbarVisible) {
            Snackbar(
                action = {
                    Button(onClick = { snackbarVisible = false }) {
                        Text("OK")
                    }
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(snackbarMessage)
            }
        }

        Button(onClick = { saveCharacter() }, Modifier.align(Alignment.CenterHorizontally)) {
            Text("Salvar Personagem")
        }

        Button(onClick = { viewCharacter() }, Modifier.align(Alignment.CenterHorizontally)) {
            Text("Ver Personagem")
        }
    }
}

@Composable
fun AtributoInputRow(
    label: String,
    atributos: AtributosPersonagens,
    race: Raca,
    updatePontos: (Int) -> Unit,
    onError: (String) -> Unit,
    distribuicaoPontosStrategy: DistribuicaoPontosStrategy,
    onAtributoChange: (String, Int) -> Unit // New parameter to handle attribute change
) {
    var textValue by remember { mutableStateOf("") }
    val bonus = race.bonus[label] ?: 0

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, modifier = Modifier.weight(1f))

        OutlinedTextField(
            value = textValue,
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() }) {
                    textValue = newValue
                    val valorAtributo = newValue.toIntOrNull() ?: 0
                    onAtributoChange(label, valorAtributo) // Update attribute value
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier
                .weight(1f)
                .width(50.dp)
                .onFocusChanged { focusState ->
                    if (!focusState.isFocused && textValue.isNotEmpty()) {
                        val valorAtributo = textValue.toIntOrNull() ?: 0
                        if (valorAtributo < 8) {
                            onError("O valor do atributo não pode ser menor que 8")
                        } else {
                            val custo = distribuicaoPontosStrategy.calcularCustoAtributo(valorAtributo)
                            updatePontos(custo)
                        }
                    }
                }
        )

        Text(
            text = bonus.toString(),
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )

        val valorAtributo = textValue.toIntOrNull() ?: 0
        val modificador = (valorAtributo - 10) / 2

        Text(
            text = modificador.toString(),
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
    }
}


@Preview(showBackground = true)
@Composable
fun AddAttributesScreenPreview() {
    DeDCardsTheme {
        AddAttributesScreen(Character("Character", Raca.ANAO, ClassePersonagem.MAGO, AtributosPersonagens()))

    }
}