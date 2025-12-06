package ru.kulishov.smartecology.domain.model

import androidx.compose.ui.graphics.Color

data class TrashBox(
    val id:Int,
    val name:Int,
    val rools: String,
    val icon: Int,
    val color: Triple<Int,Int,Int>,
    val totalValue: Int
)
