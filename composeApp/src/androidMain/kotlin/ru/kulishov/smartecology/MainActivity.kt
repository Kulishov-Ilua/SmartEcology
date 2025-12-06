package ru.kulishov.smartecology

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kulishov.smartecology.data.getDatabaseBuilder
import ru.kulishov.smartecology.data.local.getRoomDatabase
import ru.kulishov.smartecology.data.local.repository.PersonRepositoryImpl
import ru.kulishov.smartecology.data.local.repository.SettingRepositoryImpl
import ru.kulishov.smartecology.domain.usecase.settings.GetSettingsUseCase
import ru.kulishov.smartecology.domain.usecase.settings.InsertSettingUseCase
import ru.kulishov.smartecology.presentation.ui.camera.CameraBlock
import ru.kulishov.smartecology.presentation.ui.camera.CameraView
import ru.kulishov.smartecology.presentation.ui.mainscreen.MainScreenViewModel

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val db = getRoomDatabase(getDatabaseBuilder(this))



        setContent {
            AppTheme {

                App(db)
            }

            //CameraBlock()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    //App()
}

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = (if (isSystemInDarkTheme()) {
            darkColorScheme(
                primary =
                    Color(255,222,0),
                surface = Color.White,
                background = Color(242,242,242),
                onBackground = Color(79,79,79),
                onSurface = Color.Black
            )
        } else{
            lightColorScheme(primary =
                Color(255,222,0),
                surface = Color.White,
                        background = Color(242,242,242),
            onBackground = Color(79,79,79),
            onSurface = Color.Black

            )
        }),
        typography = Typography(
            bodyMedium = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground
            ),
            titleLarge = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        ),
        content = content,
    )
}