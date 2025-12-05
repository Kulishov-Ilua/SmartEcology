package ru.kulishov.smartecology

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform