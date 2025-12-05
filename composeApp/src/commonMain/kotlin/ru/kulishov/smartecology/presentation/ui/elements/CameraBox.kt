package ru.kulishov.smartecology.presentation.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CameraBox(size: Int, content: @Composable (() -> Unit)) {
    val color = MaterialTheme.colorScheme.primary
    Box(Modifier.width(size.dp).height(size.dp), contentAlignment = Alignment.TopStart) {
        val borderSize = (size / 4).dp
        Box(
            Modifier.size(borderSize).border(
                width = 10.dp,
                color,
                RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 0.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                )
            )
        )
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.TopEnd) {
            Box(
                Modifier.size(borderSize).border(
                    width = 10.dp,
                    color,
                    RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 20.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                )
            )
        }
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomStart) {
            Box(
                Modifier.size(borderSize).border(
                    width = 10.dp,
                    color,
                    RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 20.dp,
                        bottomEnd = 0.dp
                    )
                )
            )
        }
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
            Box(
                Modifier.size(borderSize).border(
                    width = 10.dp,
                    color,
                    RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 20.dp
                    )
                )
            )
        }
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Box(Modifier.size(size.dp - 20.dp).clip(RoundedCornerShape(3))) {
                content()
            }
        }
    }
}