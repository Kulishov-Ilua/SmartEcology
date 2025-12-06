package ru.kulishov.smartecology.presentation.ui.elements.quize

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.kulishov.smartecology.data.questionListData
import ru.kulishov.smartecology.domain.model.Question
import ru.kulishov.smartecology.presentation.ui.camera.BaseViewModel
import ru.kulishov.smartecology.presentation.ui.mainscreen.MainScreenViewModel

class QuizeViewModel(): BaseViewModel() {
    private val _uiState = MutableStateFlow<MainScreenViewModel.UiState>(MainScreenViewModel.UiState.Success)
    val uiState: StateFlow<MainScreenViewModel.UiState> = _uiState.asStateFlow()

    private val _questionList = MutableStateFlow<List<Question>>(questionListData)
    val questionList: StateFlow<List<Question>> = _questionList.asStateFlow()

    private val _answer = MutableStateFlow<String>("")
    val answer: StateFlow<String> = _answer.asStateFlow()

    private val _state = MutableStateFlow<Int>(0)
    val state: StateFlow<Int> = _state.asStateFlow()

    fun setState(state: Int){
        _state.value=state
    }

    fun setAnswer(textString: String){
        _answer.value+= '\n' + textString
    }


    sealed class UiState {
        object Loading : UiState()
        object Success : UiState()
        data class Error(val message: String) : UiState()
    }
}