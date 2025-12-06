package ru.kulishov.smartecology.presentation.ui.camera

import android.content.Context
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import java.io.File
import java.text.SimpleDateFormat
import java.util.Base64
import java.util.Date
import java.util.Locale
import java.util.concurrent.Executors

@Composable
fun CameraPreview(shot: Boolean, onSaved:(String)-> Unit){
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val cameraExecutor = remember { Executors.newSingleThreadExecutor() }

    var imageCapture by remember { mutableStateOf<ImageCapture?>(null) }
    var lastPhotoUri by remember { mutableStateOf<Uri?>(null) }

    var previewUseCase: androidx.camera.core.Preview? by remember { mutableStateOf(null) }


    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = { ctx ->
                PreviewView(ctx).apply {
                    scaleType = PreviewView.ScaleType.FILL_CENTER
                    implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                }
            },
            modifier = Modifier.fillMaxSize(),
            update = { previewView ->

                val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

                cameraProviderFuture.addListener({
                    try {
                        val cameraProvider = cameraProviderFuture.get()


                        val preview = androidx.camera.core.Preview.Builder()
                            .build()
                            .also {
                                it.setSurfaceProvider(previewView.surfaceProvider)
                            }

                        previewUseCase = preview

                        val capture = ImageCapture.Builder()
                            .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                            .build()

                        imageCapture = capture

                        val cameraSelector = CameraSelector.Builder()
                            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                            .build()


                        cameraProvider.unbindAll()

                        cameraProvider.bindToLifecycle(
                            lifecycleOwner,
                            cameraSelector,
                            preview,
                            capture
                        )


                    } catch (e: Exception) {
                        Log.e("CameraPreview", "Error starting camera", e)
                    }
                }, ContextCompat.getMainExecutor(context))
            }
        )
    }
    if(shot){
        takePhoto(context,imageCapture, onSaved = { onSaved(it) })
    }

    DisposableEffect(Unit) {
        onDispose {
            cameraExecutor.shutdown()

            val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
            cameraProviderFuture.addListener({
                try {
                    val cameraProvider = cameraProviderFuture.get()
                    cameraProvider.unbindAll()
                } catch (e: Exception) {
                    Log.e("CameraPreview", "Error disposing camera", e)
                }
            }, ContextCompat.getMainExecutor(context))
        }
    }
}

private fun takePhoto(
    context: Context,
    imageCapture: ImageCapture?,
    onSaved: (String)-> Unit
) {
    val capture = imageCapture ?: return

    val photoFile = File(
        context.externalMediaDirs.firstOrNull(),
        "${SimpleDateFormat("yyyyMMdd-HHmmss", Locale.US).format(Date())}.jpg"
    )

    val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

    capture.takePicture(
        outputOptions,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                val imageFile = photoFile
                 val imageBytes = imageFile.readBytes()
                val base64Image = Base64.getEncoder().encodeToString(imageBytes)
                onSaved(base64Image)
            }

            override fun onError(exception: ImageCaptureException) {
                Log.e("Camera", "Error taking photo", exception)
            }
        }
    )
}