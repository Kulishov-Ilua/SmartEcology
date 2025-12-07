package ru.kulishov.smartecology.presentation.ui.mainscreenblocs.inputblock

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.dp
import ru.kulishov.smartecology.presentation.ui.camera.CameraBlock
import ru.kulishov.smartecology.presentation.ui.elements.ButtonCustom
import ru.kulishov.smartecology.presentation.ui.elements.CameraBox
import ru.kulishov.smartecology.presentation.ui.elements.SwitcherCustom
import ru.kulishov.smartecology.presentation.ui.elements.chatbot.ChatBotUI
import ru.kulishov.smartecology.presentation.ui.elements.quize.QuizeUI
import ru.kulishov.smartecology.presentation.ui.elements.quize.QuizeViewModel
import ru.kulishov.smartecology.presentation.ui.mainscreen.MainScreenViewModel

@Composable
fun InputBlockUI(viewModel: InputBlockViewModel){
    val inputState = viewModel.inputState.collectAsState()
    var shot by remember { mutableStateOf(false) }

    val cameraState = viewModel.cameraAccept.collectAsState()
    val inpTextState = viewModel.textInpAccept.collectAsState()
    val quizeState = viewModel.quizetextInpAccept.collectAsState()

    var corouselList by remember { mutableStateOf(emptyList<String>()) }
    if(cameraState.value&&!corouselList.contains("Картинка")) corouselList+="Картинка"
    if(inpTextState.value&&!corouselList.contains("Текст")) corouselList+="Текст"
    if(quizeState.value&&!corouselList.contains("Квиз")) corouselList+="Квиз"


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
                corouselList,//listOf("Картинка", "Текст", "Квиз"),
                if (inputState.value==0) "Картинка" else if (inputState.value==1)"Текст" else "Квиз",
                { it-> viewModel.setInputState(
                    when(it){
                        "Картинка" ->{
                            0
                        }
                        "Текст" -> {
                            1
                        }
                        else ->{
                            2
                        }
                    } )})
            if(inputState.value==0){
                CameraBox(400, { CameraBlock({viewModel.onImagePrompt(it)
                    shot=false
                },shot) })
            }else if(inputState.value==1){

                Box(){
                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(50.dp)) {
                        Text("Расскажите, что вы хотите выбросить", style = MaterialTheme.typography.titleLarge)
                        Box(modifier = Modifier.fillMaxWidth()){
                            ChatBotUI(viewModel.textFieldViewModel,"Опишите подробно, что предстоит выбросить")
                        }

                    }
                }
            }else{
                Box(modifier = Modifier.padding(start = 20.dp,end=20.dp)){
                    QuizeUI(viewModel.quizeViewModel,{
                        viewModel.onAnswer(it)
                    viewModel.setInputState(1)})
                }

            }


            ButtonCustom({
                if(inputState.value==0){
                    shot=true
                }else{
                    viewModel.onTextPrompt(viewModel.textFieldViewModel.input.value)
                }
            }, text = "Как быть с мусором", colors = ButtonDefaults.buttonColors().copy(contentColor = MaterialTheme.colorScheme.onSurface))
        }
    }

}