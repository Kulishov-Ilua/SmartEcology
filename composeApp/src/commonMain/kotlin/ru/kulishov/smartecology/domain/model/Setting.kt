package ru.kulishov.smartecology.domain.model



data class Setting(
    val id: Int,
    val quizeState: Boolean,
    val textState: Boolean,
    val imageState: Boolean,

    val factsState: Boolean,
    val topListState:Boolean,
    val quizeGameState: Boolean,
    val smartStatisticState: Boolean,

    val startQuize: List<StartQuize>,
    val facts: List<String>,
    val quizeGame: List<QuizeGame>,

    val boxes: List<TrashBox>,
    val activities: List<String>,
    val persons: List<Int> //person id
)
