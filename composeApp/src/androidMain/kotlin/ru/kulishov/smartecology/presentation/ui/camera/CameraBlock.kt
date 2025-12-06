package ru.kulishov.smartecology.presentation.ui.camera

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

@Composable
actual fun CameraBlock(onSaved: (String) -> Unit,shot: Boolean) {
    val context = LocalContext.current
    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    if (hasCameraPermission) {
        CameraPreview(shot = shot,{onSaved(it)})
    } else {
        PermissionScreen {
            hasCameraPermission = it
        }
    }
}
