package ru.kulishov.smartecology.presentation.ui.mainscreenblocs.inputblock

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import ru.kulishov.smartecology.data.questionListData
import ru.kulishov.smartecology.domain.model.Setting
import ru.kulishov.smartecology.domain.model.StartQuize
import ru.kulishov.smartecology.domain.usecase.settings.GetSettingsUseCase
import ru.kulishov.smartecology.presentation.ui.camera.BaseViewModel
import ru.kulishov.smartecology.presentation.ui.elements.chatbot.ChatBotViewModel
import ru.kulishov.smartecology.presentation.ui.elements.quize.QuizeViewModel
import ru.kulishov.smartecology.presentation.ui.mainscreen.MainScreenViewModel


class InputBlockViewModel (
    val onTextPrompt:(String)-> Unit,
    val onImagePrompt:(String)-> Unit,
    val onAnswer:(String) -> Unit
): BaseViewModel(){


    private val _inputState = MutableStateFlow<Int>(0)
    val inputState: StateFlow<Int> = _inputState.asStateFlow()



    private val _cameraAccept = MutableStateFlow<Boolean>(false)
    val cameraAccept: StateFlow<Boolean> = _cameraAccept.asStateFlow()
    private val _textInpAccept = MutableStateFlow<Boolean>(false)
    val textInpAccept: StateFlow<Boolean> = _textInpAccept.asStateFlow()

    private val _quizeInpAccept = MutableStateFlow<Boolean>(false)
    val quizetextInpAccept: StateFlow<Boolean> = _quizeInpAccept.asStateFlow()

    private val _startQuize = MutableStateFlow<List<StartQuize>>(emptyList())
    val startQuize: StateFlow<List<StartQuize>> = _startQuize.asStateFlow()
    private val _uiState = MutableStateFlow<UiState>(UiState.Success)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    val quizeViewModel= QuizeViewModel()
    val textFieldViewModel= ChatBotViewModel()

    fun setData(    cameraAcceptIn: Boolean,
                    textInpAcceptIn: Boolean,
                    quizeAcceptIn: Boolean,){
        _cameraAccept.value=cameraAcceptIn
        _textInpAccept.value=textInpAcceptIn
        _quizeInpAccept.value=quizeAcceptIn
    }



    fun setInputState(state: Int) {
        launch {
            _inputState.value = state
        }

    }

    fun setState(state: UiState){
        _uiState.value=state
    }

    sealed class UiState {

        object Loading : UiState()
        object Success : UiState()

        data class Error(val message: String) : UiState()
    }
}