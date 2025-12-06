package ru.kulishov.smartecology.data.local.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class SettingsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val quizeState: Boolean,
    val textState: Boolean,
    val imageState: Boolean,

    val factsState: Boolean,
    val topListState:Boolean,
    val quizeGameState: Boolean,
    val smartStatisticState: Boolean,

    val startQuize: String,
    val facts: String,
    val quizeGame: String,

    val boxes: String,
    val activities: String,

    val persons: String
)
