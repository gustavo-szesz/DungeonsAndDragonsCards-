package com.ded

import Raca.Raca
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import android.graphics.Typeface
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ded.classes.ClassePersonagem
import com.ded.ui.theme.DeDCardsTheme
import AtributosPersonagens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstScreen(modifier: Modifier, onAddAttributesClick: (Character) -> Unit = {}) {
    val context = LocalContext.current
    val myFont = Font(
        resId = R.font.im_fell_english_sc_regular,
        weight = FontWeight.Normal
    )

    val myTextStyle = TextStyle(
        fontFamily = FontFamily(myFont),
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFFcf0c1c), // Red color
        textAlign = androidx.compose.ui.text.style.TextAlign.Center
    )

    var name by remember { mutableStateOf("") }
    var race by remember { mutableStateOf<Raca?>(null) }
    var characterClass by remember { mutableStateOf<ClassePersonagem?>(null) }
    var expandedRace by remember { mutableStateOf(false) }
    var expandedClass by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.background_first),
                contentScale = ContentScale.FillBounds
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF06171e)) // Darker blue background
                .padding(vertical = 19.dp)
        ) {
            Text(
                text = "Criação de personagens",
                style = myTextStyle,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            val textFieldColors = TextFieldDefaults.textFieldColors(
                disabledTextColor = Color.Gray,
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color(0xFF3392ae), // Blue color
                unfocusedIndicatorColor = Color(0xFF3392ae), // Blue color
                disabledIndicatorColor = Color.Gray
            )

            Text(
                text = "Utilize os campos abaixo para definir o Nome, Raça e Classe do seu personagem",
                color = Color(0xFFebebeb), // White color
                style = myTextStyle,
                fontSize = 23.sp,
                modifier = Modifier
                    .padding(bottom = 70.dp)
                    .padding(horizontal = 20.dp) // Add horizontal padding
            )

            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nome do personagem", fontSize = 18.sp, color = Color(0xFFebebeb)) }, // White color
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                colors = textFieldColors,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box {
                TextField(
                    value = race?.name ?: "",
                    onValueChange = { },
                    label = { Text("Raça", fontSize = 18.sp, color = Color(0xFFebebeb)) }, // White color
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                        .clickable { expandedRace = true },
                    colors = textFieldColors,
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { expandedRace = true }) {
                            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null, tint = Color(0xFFebebeb)) // White color
                        }
                    }
                )
                DropdownMenu(
                    expanded = expandedRace,
                    onDismissRequest = { expandedRace = false }
                ) {
                    Raca.values().forEach { raca ->
                        DropdownMenuItem(
                            text = { Text(raca.name) },
                            onClick = {
                                race = raca
                                expandedRace = false
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box {
                TextField(
                    value = characterClass?.name ?: "",
                    onValueChange = { },
                    label = { Text("Classe", fontSize = 18.sp, color = Color(0xFFebebeb)) }, // White color
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                        .clickable { expandedClass = true },
                    colors = textFieldColors,
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { expandedClass = true }) {
                            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null, tint = Color(0xFFebebeb)) // White color
                        }
                    }
                )
                DropdownMenu(
                    expanded = expandedClass,
                    onDismissRequest = { expandedClass = false }
                ) {
                    ClassePersonagem.values().forEach { classe ->
                        DropdownMenuItem(
                            text = { Text(classe.name) },
                            onClick = {
                                characterClass = classe
                                expandedClass = false
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (name.isNotEmpty() && race != null && characterClass != null) {
                        val character = Character(
                            name = name,
                            race = race!!,
                            characterClass = characterClass!!,
                            atributos = AtributosPersonagens() // Passando os atributos
                        )
                        onAddAttributesClick(character)
                    }
                },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 0.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFcf0c1c)), // Red color
                shape = RoundedCornerShape(topStart = 18.dp, topEnd = 5.dp, bottomEnd = 18.dp, bottomStart = 10.dp)
            ) {
                Text("Adicionar atributos", color = Color(0xFFebebeb)) // White color
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewFirstScreen(){
    DeDCardsTheme() {
        FirstScreen(Modifier)
    }
}

