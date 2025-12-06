package ru.kulishov.smartecology.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Timings(
    @SerialName("cache_n")
    val cacheN: Int? = null,
    @SerialName("prompt_n")
    val promptN: Int? = null,
    @SerialName("prompt_ms")
    val promptMs: Double? = null,
    @SerialName("prompt_per_token_ms")
    val promptPerTokenMs: Double? = null,
    @SerialName("prompt_per_second")
    val promptPerSecond: Double? = null,
    @SerialName("predicted_n")
    val predictedN: Int? = null,
    @SerialName("predicted_ms")
    val predictedMs: Double? = null,
    @SerialName("predicted_per_token_ms")
    val predictedPerTokenMs: Double? = null,
    @SerialName("predicted_per_second")
    val predictedPerSecond: Double? = null
)