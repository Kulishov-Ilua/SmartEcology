package ru.kulishov.smartecology.presentation.ui.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingCards(
    name: String,
    state: Boolean,
    onValueChange:(Boolean)-> Unit
){
    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
        Row(Modifier.padding(start=20.dp, end=20.dp, top=10.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Text(name, style = MaterialTheme.typography.bodyMedium)
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd){
                Switch(state,{onValueChange(it)})
            }
        }
    }
}