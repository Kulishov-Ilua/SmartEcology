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
import ru.kulishov.smartecology.presentation.ui.camera.CameraView
import ru.kulishov.smartecology.presentation.ui.elements.CameraBox
import ru.kulishov.smartecology.presentation.ui.elements.InfoCard
import ru.kulishov.smartecology.presentation.ui.elements.MagicBottomIsland
import ru.kulishov.smartecology.presentation.ui.elements.SwitcherCustom
import ru.kulishov.smartecology.presentation.ui.elements.TextFieldCustom
import ru.kulishov.smartecology.presentation.ui.elements.TrashBox
import ru.kulishov.smartecology.presentation.ui.mainscreen.MainScreenUI
import ru.kulishov.smartecology.presentation.ui.mainscreen.MainScreenViewModel

import smartecology.composeapp.generated.resources.Res
import smartecology.composeapp.generated.resources.compose_multiplatform
import smartecology.composeapp.generated.resources.trash

@Composable
@Preview
fun App() {
        var showContent by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val mainScreenViewModel= MainScreenViewModel()
            MainScreenUI(true, mainScreenViewModel)

        }
}




