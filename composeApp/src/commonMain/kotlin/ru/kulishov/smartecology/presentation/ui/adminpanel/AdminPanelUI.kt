package ru.kulishov.smartecology.presentation.ui.adminpanel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.kulishov.smartecology.presentation.ui.elements.authorized.AuthorizedBlock

@Composable
fun AdminPanel(
    viewModel: AdminPanelViewModel
){
    val uiState = viewModel.uiState.collectAsState()

    when(uiState.value){
        is AdminPanelViewModel.UiState.Error -> {

        }
        AdminPanelViewModel.UiState.NotPassword -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                AuthorizedBlock(viewModel.authorizedBlockViewModel)
            }
        }
        AdminPanelViewModel.UiState.Success -> {
            Box(Modifier.fillMaxSize().background(Color.Red))
        }
        AdminPanelViewModel.UiState.UnAuthorized -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                AuthorizedBlock(viewModel.authorizedBlockViewModel)
            }

        }
    }
}