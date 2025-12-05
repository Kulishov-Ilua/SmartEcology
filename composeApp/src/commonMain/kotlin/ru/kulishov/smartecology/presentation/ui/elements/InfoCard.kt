package ru.kulishov.smartecology.presentation.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import smartecology.composeapp.generated.resources.Res
import smartecology.composeapp.generated.resources.info

@Composable
fun InfoCard(text: String) {
    Box(
        Modifier.fillMaxWidth().heightIn(min = 80.dp)
            .background(MaterialTheme.colorScheme.background, shape = RoundedCornerShape(10))
    ){
        Box(Modifier.padding(top=5.dp, bottom = 15.dp, start = 10.dp, end = 10.dp)){
            Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    Icon(
                        painter = painterResource(Res.drawable.info),
                        tint = MaterialTheme.colorScheme.onBackground,
                        contentDescription = "info"
                    )
                    Text("Интересно", style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold

                    ))

                }
                Box(Modifier.padding(start = 15.dp)){
                    Text(text, style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium

                    ))
                }

            }
        }
    }
}