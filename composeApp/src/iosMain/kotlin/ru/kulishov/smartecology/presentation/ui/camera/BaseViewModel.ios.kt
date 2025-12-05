package ru.kulishov.smartecology.presentation.ui.camera

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

actual abstract class BaseViewModel actual constructor() {
    actual val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob()+ Dispatchers.Main)

    actual open fun onCleared(){
        coroutineScope.cancel()
    }

    actual fun launch(block: suspend CoroutineScope.()->Unit): Job = coroutineScope.launch { block() }
}