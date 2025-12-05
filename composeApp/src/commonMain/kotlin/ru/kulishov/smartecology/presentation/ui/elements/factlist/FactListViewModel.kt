package ru.kulishov.smartecology.presentation.ui.elements.factlist

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.kulishov.smartecology.presentation.ui.camera.BaseViewModel
import ru.kulishov.smartecology.presentation.ui.mainscreen.MainScreenViewModel

class FactListViewModel() : BaseViewModel() {
    private val _uiState = MutableStateFlow<MainScreenViewModel.UiState>(MainScreenViewModel.UiState.Success)
    val uiState: StateFlow<MainScreenViewModel.UiState> = _uiState.asStateFlow()

    private var _factList = MutableStateFlow<List<String>>(
        listOf(
            "В России образуется 29,8 миллиона тонн органических отходов, но утилизируется лишь 11,4% из них.",
            "Ежегодно образуется 1,2 миллиона тонн электронных отходов, из которых перерабатывается только 15%. Из них извлекаются ценные металлы: золото (1,2 тонны), серебро (18 тонн), медь (14 500 тонн)."
        )
    )

    val factList: StateFlow<List<String>> = _factList.asStateFlow()


    sealed class UiState {
        object Loading : UiState()
        object Success : UiState()
        data class Error(val message: String) : UiState()
    }
}