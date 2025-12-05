package ru.kulishov.smartecology.presentation.ui.mainscreen


import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.kulishov.smartecology.presentation.ui.camera.BaseViewModel

class MainScreenViewModel() : BaseViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Success)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _infoState = MutableStateFlow<String>("Факты")
    val infoState: StateFlow<String> = _infoState.asStateFlow()

    private val _inputState = MutableStateFlow<Boolean>(true)
    val inputState: StateFlow<Boolean> = _inputState.asStateFlow()

    private val _orientation = MutableStateFlow<Boolean>(true)
    val orientation: StateFlow<Boolean> = _orientation.asStateFlow()

    private val _activities = MutableStateFlow<List<String>>(listOf("Факты", "Лидерборд"))
    val activities: StateFlow<List<String>> = _activities.asStateFlow()

    private val _activitiesMap = MutableStateFlow<Map<String, String>>(mapOf())
    val activitiesMap: StateFlow<Map<String, String>> = _activitiesMap.asStateFlow()


    fun setInputState(state: Boolean) {
        launch {
            _inputState.value = state
        }

    }

    fun setOrientation(state: Boolean) {
        launch {
            _orientation.value = state
        }
    }

    fun setInfoBlock(state: String) {
        _infoState.value = state
    }


    sealed class UiState {
        object Loading : UiState()
        object Success : UiState()
        data class Error(val message: String) : UiState()
    }
}