package ru.kulishov.smartecology.presentation.ui.elements.toplist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.kulishov.smartecology.domain.model.Person

@Composable
fun TopScreen(persons:List<Person>){
    val sortedList = persons.sortedByDescending { it.score }
    if(sortedList.size>0){
        Box(Modifier.padding(20.dp).fillMaxWidth(), contentAlignment = Alignment.TopStart){
            LazyColumn(verticalArrangement = Arrangement.spacedBy(15.dp)) {
                var x=0
                items(sortedList) {person ->
                    x++
                    TopElement(person.name,sortedList[0].score,person.score,x)
                }
            }
        }

    }

}