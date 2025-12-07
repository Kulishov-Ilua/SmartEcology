package ru.kulishov.smartecology.presentation.ui.elements.authorized

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.kulishov.smartecology.presentation.ui.elements.ButtonCustom
import ru.kulishov.smartecology.presentation.ui.elements.TextFieldCustom

@Composable
fun AuthorizedBlock(viewModel: AuthorizedBlockViewModel){
    val uiState=viewModel.uiState.collectAsState()
    val password = viewModel.password.collectAsState()
    val isError = viewModel.isError.collectAsState()
    val secondPassword = viewModel.secondPassword.collectAsState()
    val name = viewModel.name.collectAsState()
    val users = viewModel.users.collectAsState()

    Box(modifier = Modifier.background(MaterialTheme.colorScheme.background, shape = RoundedCornerShape(20.dp)).width(300.dp), contentAlignment = Alignment.Center){
        Column(modifier = Modifier.padding(25.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(25.dp)) {
            when(uiState.value){
                AuthorizedBlockViewModel.UiState.Admin ->{
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                        Text("Здравствуйте, ${viewModel.nameInp}!\nВведите пароль", style = MaterialTheme.typography.titleMedium.copy(
                            textAlign = TextAlign.Center
                        ))
                    }

                    Box(Modifier.width(200.dp), contentAlignment = Alignment.Center){
                        TextFieldCustom(password.value, onTextChange = {viewModel.setPassword(it)}, readOnly = false, placeholder = "Введите пароль",isError.value,true,false,3)
                    }
                     Box(Modifier.width(200.dp), contentAlignment = Alignment.Center){

                    }
                    ButtonCustom({
                        viewModel.checkPassword()
                    }, text = "Войти")
                }
                AuthorizedBlockViewModel.UiState.AdminEmpty ->{
                    Text("Здравствуйте\n Придумайте пароль",style = MaterialTheme.typography.titleMedium.copy(
                        textAlign = TextAlign.Center
                    ))
                    Box(Modifier.width(200.dp), contentAlignment = Alignment.Center){
                        TextFieldCustom(password.value, onTextChange = {viewModel.setPassword(it)}, readOnly = false, placeholder = "Введите пароль",isError.value,true,false,3)

                    }
                     Box(Modifier.width(200.dp), contentAlignment = Alignment.Center){
                         TextFieldCustom(secondPassword.value, onTextChange = {viewModel.setSecondPassword(it)}, readOnly = false, placeholder = "Повторите пароль",isError.value,true,false,3)

                     }
                      ButtonCustom({
                        viewModel.checkSecondPassword()
                    }, text = "Войти")
                }
                AuthorizedBlockViewModel.UiState.User ->{
                    Text("Здравствуйте, ${viewModel.nameInp}!\nВведите пароль", style = MaterialTheme.typography.titleLarge)
                    Box(Modifier.width(200.dp), contentAlignment = Alignment.Center){
                        TextFieldCustom(password.value, onTextChange = {viewModel.setPassword(it)}, readOnly = false, placeholder = "Введите пароль",isError.value,true,false,3)

                    }
                    ButtonCustom({
                        viewModel.checkPassword()
                    }, text = "Войти")
                }
                AuthorizedBlockViewModel.UiState.UserEmpty ->{
                    Text("Здравствуйте!\nВведите имя", style = MaterialTheme.typography.titleLarge)
                    Box(Modifier.width(200.dp), contentAlignment = Alignment.Center){
                        TextFieldCustom(name.value, onTextChange = {viewModel.setName(it)}, readOnly = false, placeholder = "Введите имя",isError.value,false,false,3)

                    }
                     ButtonCustom({
                        viewModel.checkUser()
                         viewModel.setState(AuthorizedBlockViewModel.UiState.UserList)
                    }, text = "Зарегистрироваться")
                }

                is AuthorizedBlockViewModel.UiState.Error -> {
                }

                AuthorizedBlockViewModel.UiState.UserList -> {
                    Box(Modifier.padding(20.dp).height(400.dp).fillMaxSize(), contentAlignment = Alignment.Center){
                        LazyColumn {
                            item {
                                ButtonCustom({
                                    viewModel.setState(AuthorizedBlockViewModel.UiState.UserEmpty)
                                }, text = "Познакомимся?")
                            }
                            items(users.value){user->
                                Box(Modifier.clickable{
                                    viewModel.onCheckUser(user)
                                }.fillMaxWidth().height(70.dp), contentAlignment = Alignment.Center){
                                    Text(user.name, style = MaterialTheme.typography.bodyMedium)
                                }

                            }
                        }
                    }
                }
            }
        }
    }


}