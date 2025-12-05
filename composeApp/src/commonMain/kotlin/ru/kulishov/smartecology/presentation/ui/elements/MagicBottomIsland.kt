package ru.kulishov.smartecology.presentation.ui.elements

import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MagicBottomIsland(
    state: Int,
    states: List<Int>,
    onChange: (Int) -> Unit,
    content: @Composable (() -> Unit)
) {
    var animateHeight = animateDpAsState(
        targetValue = if (state >= states.size || state < 0) {
            100.dp
        } else {
            states[state].dp
        },
        animationSpec = tween(600)
    )
    if (states.size > state && state >= 0) {
        if (states[state] > 0) {
            Box(
                Modifier.fillMaxWidth().height(animateHeight.value).background(
                    MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(10.dp, 10.dp, 0.dp, 0.dp)
                ), contentAlignment = Alignment.Center
            ){
                if(animateHeight.value==states[state].dp){
                    content()
                }
            }
        }

    }

}