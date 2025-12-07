package ru.kulishov.smartecology.presentation.ui.adminpanel

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import ru.kulishov.smartecology.domain.model.Setting
import ru.kulishov.smartecology.presentation.ui.elements.ButtonCustom
import ru.kulishov.smartecology.presentation.ui.elements.SettingCards
import ru.kulishov.smartecology.presentation.ui.elements.TextFieldCustom
import ru.kulishov.smartecology.presentation.ui.elements.authorized.AuthorizedBlock
import smartecology.composeapp.generated.resources.Res
import smartecology.composeapp.generated.resources.trash

@Composable
fun AdminPanel(
    viewModel: AdminPanelViewModel
){
    val uiState = viewModel.uiState.collectAsState()
    val cameraState = viewModel.cameraAccept.collectAsState()
    val textInp = viewModel.textInpAccept.collectAsState()
    val quizeAccept = viewModel.quizetextInpAccept.collectAsState()

    val topListState = viewModel.topListState.collectAsState()
    val factState = viewModel.factAccept.collectAsState()
    val quizeGameState = viewModel.quizeGameState.collectAsState()
    val activities = viewModel.activities.collectAsState()

    val quize = viewModel.startQuize.collectAsState()
    val facts = viewModel.facts.collectAsState()
    val boxes = viewModel.boxes.collectAsState()

    when(uiState.value){
        is AdminPanelViewModel.UiState.Error -> {

        }
        AdminPanelViewModel.UiState.NotPassword -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                AuthorizedBlock(viewModel.authorizedBlockViewModel)
            }
        }
        AdminPanelViewModel.UiState.Success -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                LazyColumn(horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(700.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp)) {
                    item {
                        SettingCards("Камера",cameraState.value,{viewModel.setCamera(it)})
                    }
                    item {
                        SettingCards("Чат",textInp.value,{viewModel.setTextInp(it)})
                    }
                    item {
                        SettingCards("Квиз",quizeAccept.value,{viewModel.setQuizeState(it)})
                    }
                    item {
                        SettingCards("Топ",topListState.value,{viewModel.setTopAccept(it)} )
                    }
                    item {
                        SettingCards("Факты",factState.value,{viewModel.setFactState(it)} )
                    }
                    item {
                        SettingCards("Квиз игра",quizeGameState.value,{viewModel.setQuizeGameState(it)} )
                    }
                    item {
                        Box(Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.background,
                            RoundedCornerShape(10)
                        ), contentAlignment = Alignment.Center){
                            Column(Modifier.padding(start=20.dp, end=20.dp, top=10.dp, bottom = 10.dp),
                                verticalArrangement = Arrangement.spacedBy(15.dp)) {
                                Text( "WebView", style = MaterialTheme.typography.bodyLarge)
                                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                                    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                                        for(x in activities.value){
                                            Row(horizontalArrangement = Arrangement.spacedBy(5.dp),
                                                verticalAlignment = Alignment.CenterVertically) {
                                                Box(Modifier.weight(2f)) {
                                                        TextFieldCustom(
                                                            x.split("$$")[0],
                                                            onTextChange = {},
                                                            readOnly = true,
                                                            placeholder = "Введите имя",
                                                            false,
                                                            false,
                                                            false,
                                                            3
                                                        )

                                                }
                                                Box(Modifier.weight(2f)) {
                                                    TextFieldCustom(
                                                        x.split("$$")[1],
                                                        onTextChange = {},
                                                        readOnly = true,
                                                        placeholder = "Введите имя",
                                                        false,
                                                        false,
                                                        false,
                                                        3
                                                    )
                                                }
                                                Box(Modifier.weight(1f)) {
                                                    Icon(
                                                        painter = painterResource(Res.drawable.trash),
                                                        contentDescription = "Trash",
                                                        tint = MaterialTheme.colorScheme.onBackground,
                                                        modifier = Modifier.clickable {
                                                            viewModel.setActivities(activities.value - x)
                                                        })
                                                }
                                            }
                                        }
                                        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                                            var newName by remember { mutableStateOf("") }
                                            var newAddress by remember { mutableStateOf("") }
                                            Box(Modifier.weight(2f)) {
                                                TextFieldCustom(
                                                    newName,
                                                    onTextChange = { newName = it },
                                                    readOnly = false,
                                                    placeholder = "Введите название",
                                                    false,
                                                    false,
                                                    false,
                                                    3
                                                )
                                            }
                                            Box(Modifier.weight(2f)) {
                                                TextFieldCustom(
                                                    newAddress,
                                                    onTextChange = { newAddress = it },
                                                    readOnly = false,
                                                    placeholder = "Введите адресс",
                                                    false,
                                                    false,
                                                    false,
                                                    3
                                                )
                                            }
                                            Box(Modifier.weight(1f)) {
                                                ButtonCustom({
                                                    viewModel.setActivities(activities.value + "$newName$$$newAddress")
                                                }, text = "Добавить")
                                            }
                                        }
                                    }

                                }
                            }
                        }
                    }


                    item {
                        ButtonCustom({
                                viewModel.saveSetting(Setting(0,viewModel.password.value,quizeAccept.value,textInp.value,
                                    cameraState.value,factState.value,topListState.value,quizeGameState.value,true,quize.value,
                                    facts.value,emptyList(),boxes.value,activities.value,emptyList()))

                        }, text = "Сохранить")
                    }
                }
            }
        }
        AdminPanelViewModel.UiState.UnAuthorized -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                AuthorizedBlock(viewModel.authorizedBlockViewModel)
            }

        }
    }
}