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
import ru.kulishov.smartecology.domain.model.TrashBox
import ru.kulishov.smartecology.presentation.ui.elements.ButtonCustom
import ru.kulishov.smartecology.presentation.ui.elements.SettingCards
import ru.kulishov.smartecology.presentation.ui.elements.TextFieldCustom
import ru.kulishov.smartecology.presentation.ui.elements.TrashBox
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
                    modifier = Modifier.padding(100.dp).width(700.dp),
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
                        if(factState.value) {
                            Box(
                                Modifier.fillMaxWidth().background(
                                    MaterialTheme.colorScheme.background,
                                    RoundedCornerShape(10)
                                ), contentAlignment = Alignment.Center
                            ) {
                                Column(
                                    Modifier.padding(
                                        start = 20.dp,
                                        end = 20.dp,
                                        top = 10.dp,
                                        bottom = 10.dp
                                    ),
                                    verticalArrangement = Arrangement.spacedBy(15.dp)
                                ) {
                                    Text("Факты", style = MaterialTheme.typography.bodyLarge)
                                    Box(
                                        Modifier.fillMaxWidth(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                                            for (x in facts.value) {
                                                Row(
                                                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    Box(Modifier.weight(4f)) {
                                                        TextFieldCustom(
                                                            x,
                                                            onTextChange = {},
                                                            readOnly = true,
                                                            placeholder = "Введите факт",
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
                                                                viewModel.setFacts(facts.value - x)
                                                            })
                                                    }
                                                }
                                            }
                                            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                                                var newName by remember { mutableStateOf("") }
                                                Box(Modifier.weight(2f)) {
                                                    TextFieldCustom(
                                                        newName,
                                                        onTextChange = { newName = it },
                                                        readOnly = false,
                                                        placeholder = "Введите факт",
                                                        false,
                                                        false,
                                                        false,
                                                        3
                                                    )
                                                }

                                                Box(Modifier.weight(1f)) {
                                                    ButtonCustom({
                                                        viewModel.setFacts(facts.value+newName)
                                                    }, text = "Добавить")
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
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
                                            if(x.contains("%%")){
                                                Row(horizontalArrangement = Arrangement.spacedBy(5.dp),
                                                    verticalAlignment = Alignment.CenterVertically) {
                                                    Box(Modifier.weight(2f)) {
                                                        TextFieldCustom(
                                                            x.split("%%")[0],
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
                                                            x.split("%%")[1],
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
                                                    viewModel.setActivities(activities.value + "$newName%%$newAddress")
                                                }, text = "Добавить")
                                            }
                                        }
                                    }

                                }
                            }
                        }
                    }
                    item {
                        Box(Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.background,
                            RoundedCornerShape(10)
                        ), contentAlignment = Alignment.Center) {
                            Column(
                                Modifier.padding(
                                    start = 20.dp,
                                    end = 20.dp,
                                    top = 10.dp,
                                    bottom = 10.dp
                                ),
                                verticalArrangement = Arrangement.spacedBy(15.dp)
                            ) {
                                Text("Контейнеры", style = MaterialTheme.typography.bodyLarge)
                                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                                    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                                        for (x in boxes.value) {
                                            Row(
                                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Box(Modifier.weight(2f)) {
                                                    TextFieldCustom(
                                                        x.name,
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
                                                            viewModel.setBoxes(boxes.value - x)
                                                        })
                                                }
                                            }
                                        }
                                        var newName by remember { mutableStateOf("") }
                                        var description by remember { mutableStateOf("") }
                                        var color by remember { mutableStateOf(Triple<Int,Int, Int>(0,0,0)) }

                                        TextFieldCustom(
                                            newName,
                                            onTextChange = { newName = it },
                                            readOnly = false,
                                            placeholder = "Введите Название",
                                            false,
                                            false,
                                            false,
                                            3
                                        )
                                        TextFieldCustom(
                                            description,
                                            onTextChange = { description = it },
                                            readOnly = false,
                                            placeholder = "Введите описание",
                                            false,
                                            false,
                                            false,
                                            3
                                        )
                                        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                                            var error1 by remember { mutableStateOf(false) }
                                            Box(Modifier.weight(1f)) {
                                                TextFieldCustom(
                                                    color.first.toString(),
                                                    onTextChange = {it->
                                                        val nInt = it.toIntOrNull()
                                                        if(nInt==null){
                                                            error1=true
                                                        }else{
                                                            color=Triple(nInt.coerceIn(0,255),color.second,color.third)
                                                        }
                                                    },
                                                    readOnly = false,
                                                    placeholder = "Введите red",
                                                    error1,
                                                    false,
                                                    false,
                                                    3
                                                )
                                            }
                                            var error2 by remember { mutableStateOf(false) }
                                            Box(Modifier.weight(1f)) {
                                                TextFieldCustom(
                                                    color.second.toString(),
                                                    onTextChange = {it->
                                                        val nInt = it.toIntOrNull()
                                                        if(nInt==null){
                                                            error2=true
                                                        }else{
                                                            color=Triple(color.first,nInt.coerceIn(0,255),color.third)
                                                        }
                                                    },
                                                    readOnly = false,
                                                    placeholder = "Введите green",
                                                    error2,
                                                    false,
                                                    false,
                                                    3
                                                )
                                            }
                                            var error3 by remember { mutableStateOf(false) }
                                            Box(Modifier.weight(1f)) {
                                                TextFieldCustom(
                                                    color.third.toString(),
                                                    onTextChange = {it->
                                                        val nInt = it.toIntOrNull()
                                                        if(nInt==null){
                                                            error3=true
                                                        }else{
                                                            color=Triple(color.first,color.second,nInt.coerceIn(0,255))
                                                        }
                                                    },
                                                    readOnly = false,
                                                    placeholder = "Введите blue",
                                                    error1,
                                                    false,
                                                    false,
                                                    3
                                                )
                                            }
                                        }
                                        ButtonCustom({
                                            viewModel.setBoxes(boxes.value + TrashBox(0,newName,description, 0, color,0) )
                                        },text = "Добавить")
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