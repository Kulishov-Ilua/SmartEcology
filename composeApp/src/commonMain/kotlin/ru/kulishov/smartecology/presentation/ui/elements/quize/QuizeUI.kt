package ru.kulishov.smartecology.presentation.ui.elements.quize

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.kulishov.smartecology.presentation.ui.elements.ButtonCustom
import ru.kulishov.smartecology.presentation.ui.elements.chatbot.ChatBotUI
import ru.kulishov.smartecology.presentation.ui.mainscreen.MainScreenViewModel

@Composable
fun QuizeUI(viewModel: QuizeViewModel, onResult:(String)-> Unit){
    val questions = viewModel.questionList.collectAsState()
    val state = viewModel.state.collectAsState()
    val result = viewModel.answer.collectAsState()

    if (state.value<questions.value.size){
        val quest = questions.value[state.value]
        Box(){
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(50.dp)) {
                Text(quest.name, style = MaterialTheme.typography.titleLarge)
                Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                    Column(modifier = Modifier.width(300.dp)) {
                        Text("Подойдут:", style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold))
                        Text(quest.good,style = MaterialTheme.typography.bodyMedium)
                    }
                    Column(modifier = Modifier.width(300.dp)) {
                        Text("Не подойдут:", style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold))
                        Text(quest.bad,style = MaterialTheme.typography.bodyMedium)
                    }

                }
                Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                    ButtonCustom({
                        viewModel.setState(state.value+1)
                    }, text = "Нет",
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.onSurface,
                            contentColor = Color.Gray,
                            disabledContentColor = Color.Black
                        ))

                    ButtonCustom({
                        viewModel.setState(state.value+1)
                        viewModel.setAnswer(quest.output)
                    }, text = "Да",colors = ButtonDefaults.buttonColors().copy(contentColor = MaterialTheme.colorScheme.onSurface))
                }


            }
        }
    }
    if(state.value>=questions.value.size){
        onResult(result.value)
    }
}