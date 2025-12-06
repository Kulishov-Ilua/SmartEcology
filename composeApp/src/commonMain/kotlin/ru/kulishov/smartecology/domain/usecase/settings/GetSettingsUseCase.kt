package ru.kulishov.smartecology.domain.usecase.settings

import kotlinx.coroutines.flow.Flow
import ru.kulishov.smartecology.domain.model.Setting
import ru.kulishov.smartecology.domain.repository.SettingRepository

class GetSettingsUseCase(
    private val repository: SettingRepository
) {
     suspend  operator  fun invoke(): Flow<List<Setting>> = repository.getSettings()
}