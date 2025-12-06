package ru.kulishov.smartecology.presentation.ui.camera

import androidx.compose.runtime.Composable

@Composable
expect fun CameraBlock(onSaved: (String ) -> Unit,shot: Boolean)