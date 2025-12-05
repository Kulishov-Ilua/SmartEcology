package ru.kulishov.smartecology.presentation.ui.elements

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import smartecology.composeapp.generated.resources.Res
import smartecology.composeapp.generated.resources.trash_a
import smartecology.composeapp.generated.resources.trash_b

@Composable
fun TrashBox(state: Boolean, icon: DrawableResource, text: String, color: Color) {
    var nstate by remember { mutableStateOf(false) }
    LaunchedEffect(1) {
        delay(100)
        nstate = state
    }


    var offsetY = animateDpAsState(
        targetValue = if (!nstate) {
            10.dp
        } else {
            20.dp
        },
        animationSpec = tween(durationMillis = 600)
    )
    var offsetx = animateDpAsState(
        targetValue = if (!nstate) {
            0.dp
        } else {
            80.dp
        },
        animationSpec = tween(delayMillis = 600, durationMillis = 600)
    )
    var rotate = animateFloatAsState(
        targetValue = if (!nstate) {
            0f
        } else {
            -45f
        },
        animationSpec = tween(delayMillis = 1200, durationMillis = 600)
    )
    Box(Modifier.width(200.dp).height(300.dp), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box() {
                Icon(
                    painterResource(Res.drawable.trash_b), contentDescription = "",
                    Modifier.padding(bottom = offsetY.value).rotate(rotate.value), tint = color
                )
            }

//        Icon(painterResource(Res.drawable.trash_a), contentDescription = "", tint = color,
//            modifier = Modifier.padding(start = offsetx.value))
            Box(
                Modifier.padding(start = offsetx.value).width(100.dp).height(117.dp)
                    .background(color, shape = RoundedCornerShape(0, 0, 15, 15)),
                contentAlignment = Alignment.Center
            ){
                Column(horizontalAlignment = Alignment.CenterHorizontally,) {
                    Icon(
                        painterResource(icon), contentDescription = "",
                         tint = MaterialTheme.colorScheme.onSurface
                    )
                    Text(text = text, style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    ))

                }
            }

        }
    }


}