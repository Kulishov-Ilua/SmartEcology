package ru.kulishov.smartecology.presentation.ui.mainscreen


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import ru.kulishov.smartecology.presentation.ui.camera.CameraBlock
import ru.kulishov.smartecology.presentation.ui.camera.CameraView
import ru.kulishov.smartecology.presentation.ui.elements.ButtonCustom
import ru.kulishov.smartecology.presentation.ui.elements.CameraBox
import ru.kulishov.smartecology.presentation.ui.elements.SwitcherCustom
import ru.kulishov.smartecology.presentation.ui.elements.factlist.FactListUI
import ru.kulishov.smartecology.presentation.ui.elements.factlist.FactListViewModel
import smartecology.composeapp.generated.resources.Res
import smartecology.composeapp.generated.resources.manual
import smartecology.composeapp.generated.resources.menu

@Composable
fun MainScreenUI(
    orientation: Boolean,
    viewModel: MainScreenViewModel
) {
    viewModel.setOrientation(orientation)
    val orientation = viewModel.orientation.collectAsState()
    val uiState = viewModel.uiState.collectAsState()
    val infoState = viewModel.infoState.collectAsState()
    val inputState = viewModel.inputState.collectAsState()
    val activities = viewModel.activities.collectAsState()

    Box(Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(start = 20.dp, end = 20.dp)
        ) {
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.manual),
                        contentDescription = "Manual",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.clickable {

                        })
                    Icon(
                        painter = painterResource(Res.drawable.menu),
                        contentDescription = "Menu",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.clickable {

                        })

                }
            }
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                when (orientation.value) {
                    true -> {
                        Row(
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.spacedBy(20.dp)
                        ) {
                            if(inputState.value){
                                Box(
                                    modifier = Modifier.fillMaxWidth(0.5f),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxSize(),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(50.dp)
                                    ) {
                                        SwitcherCustom(
                                            listOf("Картинка", "Текст"),
                                            if (inputState.value) "Картинка" else "Текст",
                                            { viewModel.setInputState(if (it == "Картинка") true else false) })
                                        CameraBox(400, { CameraBlock() })
                                        ButtonCustom({

                                        }, text = "Как быть с мусором")
                                    }
                                }
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxSize(),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(50.dp)
                                    ) {
                                        SwitcherCustom(
                                            activities.value,
                                            infoState.value,
                                            { viewModel.setInfoBlock(it)})
                                        when(infoState.value){
                                            "Факты" ->{
                                                val factListViewModel = FactListViewModel()
                                                FactListUI(factListViewModel)
                                            }
                                            "Лидерборд" ->{

                                            }
                                            else ->{

                                            }
                                        }
                                    }
                                }
                            }else{

                            }

                        }
                    }

                    false -> {

                    }
                }
            }


        }

    }


}