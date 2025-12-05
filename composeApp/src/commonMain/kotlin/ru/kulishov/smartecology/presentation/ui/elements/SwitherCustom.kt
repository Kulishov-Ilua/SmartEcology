package ru.kulishov.smartecology.presentation.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun SwitcherCustom(
    states: List<String>,
    state: String,
    onSetState: (String) -> Unit,
) {
    val unactiveTextSyle = MaterialTheme.typography.bodyMedium.copy(color = Color(46, 46, 46))
    val activeTextStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.Black)
    val boxColor = MaterialTheme.colorScheme.primary
    Box() {
        LazyRow(verticalAlignment = Alignment.CenterVertically) {
            items(states) { item ->
                Box(
                    Modifier.height(30.dp).background(
                        if (state == item) MaterialTheme.colorScheme.primary else Color.Transparent,
                        shape = RoundedCornerShape(5.dp)
                    )
                        .clickable {
                            if (state != item) {
                                onSetState(item)
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        item, style = if (state == item) activeTextStyle else unactiveTextSyle,
                        modifier = Modifier.padding(
                            top = 5.dp,
                            bottom = 5.dp,
                            start = 10.dp,
                            end = 10.dp
                        )
                    )
                }

            }
        }
    }

}