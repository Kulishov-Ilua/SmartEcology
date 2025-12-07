package ru.kulishov.smartecology.presentation.ui.mainscreen


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import ru.kulishov.smartecology.presentation.ui.adminpanel.AdminPanel
import ru.kulishov.smartecology.presentation.ui.adminpanel.AdminPanelViewModel
import ru.kulishov.smartecology.presentation.ui.camera.CameraBlock
import ru.kulishov.smartecology.presentation.ui.camera.CameraView
import ru.kulishov.smartecology.presentation.ui.elements.ButtonCustom
import ru.kulishov.smartecology.presentation.ui.elements.CameraBox
import ru.kulishov.smartecology.presentation.ui.elements.MagicBottomIsland
import ru.kulishov.smartecology.presentation.ui.elements.SwitcherCustom
import ru.kulishov.smartecology.presentation.ui.elements.TextFieldCustom
import ru.kulishov.smartecology.presentation.ui.elements.TrashBox
import ru.kulishov.smartecology.presentation.ui.elements.WebPreview
import ru.kulishov.smartecology.presentation.ui.elements.authorized.AuthorizedBlock
import ru.kulishov.smartecology.presentation.ui.elements.authorized.AuthorizedBlockViewModel
import ru.kulishov.smartecology.presentation.ui.elements.chatbot.ChatBotUI
import ru.kulishov.smartecology.presentation.ui.elements.chatbot.ChatBotViewModel
import ru.kulishov.smartecology.presentation.ui.elements.factlist.FactListUI
import ru.kulishov.smartecology.presentation.ui.elements.factlist.FactListViewModel
import ru.kulishov.smartecology.presentation.ui.elements.quize.QuizeUI
import ru.kulishov.smartecology.presentation.ui.elements.quize.QuizeViewModel
import ru.kulishov.smartecology.presentation.ui.mainscreenblocs.contentblock.ContentBlockUI
import ru.kulishov.smartecology.presentation.ui.mainscreenblocs.inputblock.InputBlockUI
import smartecology.composeapp.generated.resources.Res
import smartecology.composeapp.generated.resources.exit
import smartecology.composeapp.generated.resources.manual
import smartecology.composeapp.generated.resources.menu
import smartecology.composeapp.generated.resources.plastic
import smartecology.composeapp.generated.resources.trash

@Composable
fun MainScreenUI(
    orientation: Boolean,
    viewModel: MainScreenViewModel
) {
    viewModel.setOrientation(orientation)
    val orientation = viewModel.orientation.collectAsState()
    val uiState = viewModel.uiState.collectAsState()

    val personListState = viewModel.personListState.collectAsState()

    val textFieldViewModel= ChatBotViewModel()

    val currentAdress by remember { mutableStateOf("https://docs.google.com/presentation/d/1I2EqD1QuewortwVOdxqe-B00m-MiP6pFJn21GD0_iBc/mobilepresent?slide=id.gc6f972163_0_19") }

    Box(Modifier.padding(top = 25.dp).fillMaxSize()) {
        when(uiState.value){
            is MainScreenViewModel.UiState.Error -> {

            }
            MainScreenViewModel.UiState.Loading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }
            MainScreenViewModel.UiState.Success -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                   // modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                ) {
                    Box(Modifier.padding(start = 20.dp, end = 20.dp).fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(15.dp)
                        ) {
                            ButtonCustom(
                                onClick = {
                                    viewModel.setPersonListState()
                                }, text = if(viewModel.currentUser.value.id!=-1) viewModel.currentUser.value.name
                                else "Войти"
                            )
                            Icon(
                                painter = painterResource(Res.drawable.manual),
                                contentDescription = "Manual",
                                tint = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.clickable {
                                    viewModel.setState(MainScreenViewModel.UiState.WebView)
                                })
                            Icon(
                                painter = painterResource(Res.drawable.menu),
                                contentDescription = "Menu",
                                tint = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.clickable {
                                    viewModel.setState(MainScreenViewModel.UiState.AdminPanel)
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
                                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                                    modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                                ) {
                                    InputBlockUI(viewModel.inputBlockViewModel)
                                    ContentBlockUI(viewModel.contentBlockViewModel)
                                }
                            }

                            false -> {
                                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(50.dp)) {
                                    InputBlockUI(viewModel.inputBlockViewModel)
                                    ContentBlockUI(viewModel.contentBlockViewModel)

                                }
                            }
                        }
                    }


                }

                if(personListState.value){
                    Box(Modifier.padding(end = 20.dp).fillMaxSize(), contentAlignment = Alignment.TopEnd){
                        viewModel.usersViewModel.setState(AuthorizedBlockViewModel.UiState.UserList)
                        AuthorizedBlock(viewModel.usersViewModel)
                    }
                }

            }

            MainScreenViewModel.UiState.Accept -> {
                textFieldViewModel.setInput(viewModel.modelAnswer)
                textFieldViewModel.setReadOnly(true)
                Box(Modifier.padding(start = 20.dp, end = 20.dp).fillMaxSize(), contentAlignment = Alignment.Center){
                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(50.dp)) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(50.dp)) {
                            Text("Мои предположения", style = MaterialTheme.typography.titleLarge)
                            Box(modifier = Modifier.fillMaxWidth()){
                                ChatBotUI(textFieldViewModel,"Мои предположения")
                            }
                            Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                                ButtonCustom({
                                    textFieldViewModel.setReadOnly(false)
                                    viewModel.inputBlockViewModel.setInputState(1)
                                    viewModel.setState(MainScreenViewModel.UiState.Success)
                                }, text = "Не верно",
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.colorScheme.onSurface,
                                        contentColor = MaterialTheme.colorScheme.onSurface,
                                        disabledContentColor = Color.Gray
                                    ))

                                ButtonCustom({
                                    viewModel.textRequest(textFieldViewModel.input.value)
                                }, text = "Верно",colors = ButtonDefaults.buttonColors().copy(contentColor = MaterialTheme.colorScheme.onSurface))
                            }

                        }
                    }
                }

            }
            MainScreenViewModel.UiState.Result -> {
                textFieldViewModel.setInput(viewModel.modelAnswer)
                textFieldViewModel.setReadOnly(true)
                LazyColumn {
                    item{
                        Box(Modifier.padding(start = 20.dp, end = 20.dp, bottom = 300.dp).fillMaxSize(), contentAlignment = Alignment.TopCenter){
                            Column(horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(50.dp)) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(50.dp)) {
                                    Text("Мои рекомендации", style = MaterialTheme.typography.titleLarge)
                                    Box(modifier = Modifier.fillMaxWidth()){
                                        ChatBotUI(textFieldViewModel,"Мои рекомендации")
                                    }
                                    Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                                        ButtonCustom({
                                            textFieldViewModel.setReadOnly(false)
                                            viewModel.setState(MainScreenViewModel.UiState.Success)
                                            viewModel.inputBlockViewModel.quizeViewModel.setState(0)
                                        }, text = "Спасибо",colors = ButtonDefaults.buttonColors().copy(contentColor = MaterialTheme.colorScheme.onSurface))
                                    }


                                }
                            }
                        }
                    }
                }

                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){
                    MagicBottomIsland(1,listOf(0,300), onChange = {},{
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(50.dp)) {
                            items(viewModel.settings.value.boxes){ box->
                                TrashBox(state = viewModel.modelAnswer.contains(box.name),Res.drawable.trash,box.name,
                                    Color(box.color.first,box.color.second,box.color.third) )
                            }
                        }
//                        Row() {
//                            TrashBox(state = if (viewModel.modelAnswer.contains("пластик")||viewModel.modelAnswer.contains("Пластик")||viewModel.modelAnswer.contains("**пластик**")) true else false,Res.drawable.plastic,"Пластик",Color.Yellow)
//                            TrashBox(state = if (viewModel.modelAnswer.contains("стекло")||viewModel.modelAnswer.contains("Стекло")||viewModel.modelAnswer.contains("**стекло**")) true else false,Res.drawable.plastic,"Стекло",Color(21,0,255))
//                            TrashBox(state = if (viewModel.modelAnswer.contains("буиага")||viewModel.modelAnswer.contains("Бумага")||viewModel.modelAnswer.contains("**бумага**")) true else false,Res.drawable.plastic,"Бумага",Color(8,154,0))
//                            TrashBox(state = if (viewModel.modelAnswer.contains("другое")||viewModel.modelAnswer.contains("Другое")||viewModel.modelAnswer.contains("**другое**")) true else false,Res.drawable.plastic,"Другое",Color(204,81,39))
//
//
//                        }
                    })
                }


            }

            MainScreenViewModel.UiState.WebView -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){
                    Box(Modifier.fillMaxWidth().fillMaxHeight()){
                        Column {
                            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd){
                                Icon(painter = painterResource(Res.drawable.exit), contentDescription = "exit", tint = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.clickable{viewModel.setState(
                                        MainScreenViewModel.UiState.Success
                                    )})
                            }
                            WebPreview(currentAdress)
                        }
                    }
                }

            }

            MainScreenViewModel.UiState.AdminPanel -> {
                Column {
                    Box(Modifier.padding(start = 20.dp, end = 20.dp).fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(15.dp)
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.exit),
                                contentDescription = "Menu",
                                tint = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.clickable {
                                    viewModel.setState(MainScreenViewModel.UiState.Success)
                                    viewModel.adminPanelViewModel.setState(AdminPanelViewModel.UiState.UnAuthorized)
                                })

                        }
                    }
                }
                AdminPanel(viewModel.adminPanelViewModel)
            }
        }

    }


}