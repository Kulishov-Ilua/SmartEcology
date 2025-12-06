package ru.kulishov.smartecology.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val role: String,
    val content: String
)

