package ru.kulishov.smartecology.domain.usecase.settings

import ru.kulishov.smartecology.domain.model.Setting
import ru.kulishov.smartecology.domain.repository.SettingRepository

class SetSettingsUseCase(
    private val repository: SettingRepository
) {
    suspend operator fun invoke(setting: Setting) = repository.setSetting(setting)
}