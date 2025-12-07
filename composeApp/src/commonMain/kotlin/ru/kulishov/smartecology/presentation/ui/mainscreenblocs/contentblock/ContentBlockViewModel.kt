package ru.kulishov.smartecology.presentation.ui.mainscreenblocs.contentblock

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.kulishov.smartecology.domain.model.QuizeGame
import ru.kulishov.smartecology.presentation.ui.camera.BaseViewModel
import ru.kulishov.smartecology.presentation.ui.mainscreenblocs.inputblock.InputBlockViewModel

class ContentBlockViewModel(

): BaseViewModel() {
    private val _uiState = MutableStateFlow<InputBlockViewModel.UiState>(InputBlockViewModel.UiState.Success)
    val uiState: StateFlow<InputBlockViewModel.UiState> = _uiState.asStateFlow()
    private val _activities = MutableStateFlow<List<String>>(listOf("Факты", "Лидерборд"))
    val activities: StateFlow<List<String>> = _activities.asStateFlow()

    private val _infoState = MutableStateFlow<String>("Факты")
    val infoState: StateFlow<String> = _infoState.asStateFlow()

    private val _facts = MutableStateFlow<List<String>>(emptyList())
    val facts: StateFlow<List<String>> = _facts.asStateFlow()
    private val _factAccept = MutableStateFlow<Boolean>(false)
    val factAccept: StateFlow<Boolean> = _factAccept.asStateFlow()

    private val _topListState = MutableStateFlow<Boolean>(false)
    val topListState: StateFlow<Boolean> = _topListState.asStateFlow()

    private val _quizeGameState = MutableStateFlow<Boolean>(false)
    val quizeGameState: StateFlow<Boolean> = _quizeGameState.asStateFlow()

    private val _quizeGame = MutableStateFlow<List<QuizeGame>>(emptyList())
    val quizeGame: StateFlow<List<QuizeGame>> = _quizeGame.asStateFlow()


    fun setInfoBlock(state: String) {
        _infoState.value = state
    }
    fun setData(activities: List<String>, factAccept: Boolean, topAccept: Boolean, quizeAccept: Boolean, quizeGame:List<QuizeGame>){
        _activities.value=activities
        _factAccept.value=factAccept
        _topListState.value=topAccept
        _quizeGameState.value=quizeAccept
        _quizeGame.value=quizeGame
    }



    sealed class UiState {

        object Loading : UiState()
        object Success : UiState()

        data class Error(val message: String) : UiState()
    }
}