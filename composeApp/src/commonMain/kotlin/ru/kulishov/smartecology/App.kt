package ru.kulishov.smartecology

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.room.RoomDatabase
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ru.kulishov.smartecology.data.local.AppDatabase
import ru.kulishov.smartecology.data.local.repository.PersonRepositoryImpl
import ru.kulishov.smartecology.data.local.repository.SettingRepositoryImpl
import ru.kulishov.smartecology.domain.repository.SettingRepository
import ru.kulishov.smartecology.domain.usecase.person.AddPersonUseCase
import ru.kulishov.smartecology.domain.usecase.person.GetPersonUseCase
import ru.kulishov.smartecology.domain.usecase.settings.GetSettingsUseCase
import ru.kulishov.smartecology.domain.usecase.settings.InsertSettingUseCase
import ru.kulishov.smartecology.domain.usecase.settings.SetSettingsUseCase
import ru.kulishov.smartecology.presentation.ui.camera.CameraView
import ru.kulishov.smartecology.presentation.ui.elements.CameraBox
import ru.kulishov.smartecology.presentation.ui.elements.InfoCard
import ru.kulishov.smartecology.presentation.ui.elements.MagicBottomIsland
import ru.kulishov.smartecology.presentation.ui.elements.SwitcherCustom
import ru.kulishov.smartecology.presentation.ui.elements.TextFieldCustom
import ru.kulishov.smartecology.presentation.ui.elements.TrashBox
import ru.kulishov.smartecology.presentation.ui.mainscreen.MainScreenUI
import ru.kulishov.smartecology.presentation.ui.mainscreen.MainScreenViewModel

import smartecology.composeapp.generated.resources.Res
import smartecology.composeapp.generated.resources.compose_multiplatform
import smartecology.composeapp.generated.resources.trash

@Composable
@Preview
fun App(db: AppDatabase) {
            var showContent by remember { mutableStateOf(false) }
        val settingRepository= SettingRepositoryImpl(db.settingDao())
        val personRepository = PersonRepositoryImpl(db.personDao())

        val mainScreenViewModel= MainScreenViewModel(
            GetSettingsUseCase(settingRepository),
            InsertSettingUseCase(settingRepository), SetSettingsUseCase(settingRepository),
            AddPersonUseCase(personRepository),
            GetPersonUseCase(personRepository))
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                //.safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            MainScreenUI(true, mainScreenViewModel)

        }
}




