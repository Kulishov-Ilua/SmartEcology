package ru.kulishov.smartecology

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ru.kulishov.smartecology.presentation.ui.elements.CameraBox
import ru.kulishov.smartecology.presentation.ui.elements.InfoCard
import ru.kulishov.smartecology.presentation.ui.elements.SwitcherCustom
import ru.kulishov.smartecology.presentation.ui.elements.TextFieldCustom
import ru.kulishov.smartecology.presentation.ui.elements.TrashBox

import smartecology.composeapp.generated.resources.Res
import smartecology.composeapp.generated.resources.compose_multiplatform
import smartecology.composeapp.generated.resources.trash

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(Modifier.width(500.dp)){

//                SwitcherCustom(listOf("1","dsfsdfs","adcfdsfds"),state,{state=it}, Color.Yellow,
//                    TextStyle(), TextStyle())
//                InfoCard("В России образуется 29,8 миллиона тонн органических отходов, но утилизируется лишь 11,4% из них.",
//                    TextStyle())

            }
//            CameraBox(300,Color.Yellow,{
//                Box(Modifier.fillMaxSize().background(Color.Red))
//            })
            var state by remember { mutableStateOf("") }
//            Box(Modifier.width(500.dp)){
//                TextFieldCustom(state,{state=it},false,Color.Yellow)
//            }
            TrashBox(true, Res.drawable.trash,"Бумага", Color.Blue)

        }
    }
}