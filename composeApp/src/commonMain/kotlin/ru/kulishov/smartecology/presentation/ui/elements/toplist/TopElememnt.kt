package ru.kulishov.smartecology.presentation.ui.elements.toplist

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun TopElement(name:String, maxSize:Int,currentSize:Int,position:Int){
    var state by remember { mutableStateOf(false) }
    LaunchedEffect(1){
        delay(100)
        state=true
    }
    val animation = animateFloatAsState (targetValue = if(state) currentSize.toFloat()/if (maxSize!=0) maxSize else 1
        else 0f,
        animationSpec = tween(delayMillis = 1800*position, durationMillis = 1800)
    )

    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)) {
        Box(Modifier.width(100.dp)){
            Text(name, style = MaterialTheme.typography.bodyMedium)
        }

        Box(Modifier.height(30.dp).fillMaxWidth(animation.value).background( brush = Brush.horizontalGradient(
                if(position==1){
                    listOf(Color(255,174,0), Color(232,51,31))
                }else if(position==2) {
                    listOf(Color(109,150,255), Color(255,255,255))
                }else if(position==3){
                    listOf(Color(187,105,86), Color(223,73,73))
                }else{
                    listOf(Color.Gray, Color.Gray)
                }
                ), shape = CircleShape
        ))
    }
}
