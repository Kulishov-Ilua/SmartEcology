package ru.kulishov.smartecology.presentation.ui.elements.chatbot

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.kulishov.smartecology.presentation.ui.camera.BaseViewModel
import ru.kulishov.smartecology.presentation.ui.mainscreen.MainScreenViewModel

class ChatBotViewModel(): BaseViewModel(){
    private val _uiState = MutableStateFlow<UiState>(UiState.Success)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _input = MutableStateFlow<String>("")
    val input: StateFlow<String> = _input.asStateFlow()
    private val _readOnnly = MutableStateFlow<Boolean>(false)
    val readOnly: StateFlow<Boolean> = _readOnnly.asStateFlow()

    fun setInput(inp:String){
        _input.value=inp
    }

    fun setReadOnly(state: Boolean){
        _readOnnly.value=state
    }

    sealed class UiState {
        object Blocked : UiState()
        object Loading : UiState()
        object Success : UiState()
        data class Error(val message: String) : UiState()
    }
}