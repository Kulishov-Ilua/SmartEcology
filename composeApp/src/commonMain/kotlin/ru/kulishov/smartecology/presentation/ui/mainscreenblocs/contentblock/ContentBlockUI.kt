package ru.kulishov.smartecology.presentation.ui.mainscreenblocs.contentblock

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.kulishov.smartecology.presentation.ui.elements.SwitcherCustom
import ru.kulishov.smartecology.presentation.ui.elements.WebPreview
import ru.kulishov.smartecology.presentation.ui.elements.factlist.FactListUI
import ru.kulishov.smartecology.presentation.ui.elements.factlist.FactListViewModel
import ru.kulishov.smartecology.presentation.ui.elements.toplist.TopScreen

@Composable
fun ContentBlockUI(
    viewModel: ContentBlockViewModel
){
    val activities = viewModel.activities.collectAsState()
    val infoState = viewModel.infoState.collectAsState()
    val factAccept = viewModel.factAccept.collectAsState()
    val topAccept = viewModel.topListState.collectAsState()
    val quizeAccept = viewModel.quizeGameState.collectAsState()
    val person = viewModel.person.collectAsState()

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(50.dp)
        ) {
            val createdList = mutableListOf<String>()
            if(topAccept.value) createdList+="Лидерборд"
            if(factAccept.value) createdList+="Факты"
            if(quizeAccept.value) createdList+="Квиз"
            SwitcherCustom(
                createdList + "Oggetto",
                infoState.value,
                { viewModel.setInfoBlock(it)})
            when(infoState.value){
                "Факты" ->{
                    val factListViewModel = FactListViewModel()
                    FactListUI(factListViewModel)
                }
                "Лидерборд" ->{
                    TopScreen(person.value)
                }
                else ->{
                    WebPreview("https://oggetto.ru/")
                }
            }
        }
    }
}