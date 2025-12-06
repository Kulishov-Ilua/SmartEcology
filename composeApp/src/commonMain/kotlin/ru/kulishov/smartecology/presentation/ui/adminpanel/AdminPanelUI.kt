package ru.kulishov.smartecology.presentation.ui.adminpanel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.kulishov.smartecology.presentation.ui.elements.SettingCards
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
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                LazyColumn(horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(700.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp)) {
                    item {
                        //SettingCards("")
                    }
                }
            }
        }
        AdminPanelViewModel.UiState.UnAuthorized -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                AuthorizedBlock(viewModel.authorizedBlockViewModel)
            }

        }
    }
}