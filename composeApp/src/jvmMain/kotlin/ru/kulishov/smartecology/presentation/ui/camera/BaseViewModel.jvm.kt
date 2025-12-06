package ru.kulishov.smartecology.presentation.ui.camera

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.job
import kotlin.coroutines.EmptyCoroutineContext

actual abstract class BaseViewModel actual constructor() {
    actual val coroutineScope: CoroutineScope
        get() = CoroutineScope(EmptyCoroutineContext)

    protected actual open fun onCleared() {
    }

    actual fun launch(block: suspend CoroutineScope.() -> Unit): Job {
        return CoroutineScope(EmptyCoroutineContext).coroutineContext.job
    }
}