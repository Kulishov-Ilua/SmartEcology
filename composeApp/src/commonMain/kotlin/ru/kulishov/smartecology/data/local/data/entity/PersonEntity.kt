package ru.kulishov.smartecology.data.local.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
data class PersonEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val icon:Int,
    val password: String,
    val score: Int,
    val statistic: String
)
