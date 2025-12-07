package ru.kulishov.smartecology.domain.model

data class QuizeGame(
    val question:String,
    val variants: List<String>,
    val answer: String
)
