package ru.kulishov.smartecology.presentation.ui.elements.factlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.kulishov.smartecology.presentation.ui.elements.InfoCard

@Composable
fun FactListUI(viewModel: FactListViewModel){
    var factList  = viewModel.factList.collectAsState()

    LazyColumn(Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(15.dp)) {
        items(factList.value){
            fact->
            InfoCard(fact)
        }
    }
}