package ru.kulishov.smartecology.presentation.ui.camera

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

expect abstract class BaseViewModel() {
    val coroutineScope:CoroutineScope

    protected open fun onCleared()

    fun launch(block: suspend CoroutineScope.()->Unit): Job
}