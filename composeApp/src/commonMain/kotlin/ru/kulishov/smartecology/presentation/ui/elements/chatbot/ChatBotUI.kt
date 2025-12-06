package ru.kulishov.smartecology.presentation.ui.elements.chatbot

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import ru.kulishov.smartecology.presentation.ui.elements.TextFieldCustom

@Composable
fun ChatBotUI(viewModel: ChatBotViewModel,placeholder: String) {
    val input = viewModel.input.collectAsState()
    val uiState = viewModel.uiState.collectAsState()
    when (uiState.value) {
        ChatBotViewModel.UiState.Blocked -> {
            TextFieldCustom(input.value, { viewModel.setInput(it) }, true,placeholder)
        }

        is ChatBotViewModel.UiState.Error -> {

        }

        ChatBotViewModel.UiState.Loading -> {
            TextFieldCustom(input.value, { viewModel.setInput(it) }, true,placeholder)
        }

        ChatBotViewModel.UiState.Success -> {
            TextFieldCustom(input.value, { viewModel.setInput(it) }, false,placeholder)
        }
    }

}