package ru.kulishov.smartecology.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.kulishov.smartecology.domain.model.Setting

interface SettingRepository {
    fun getSettings(): Flow<List<Setting>>
    suspend fun setSetting(setting: Setting)

    suspend fun insertSetting(setting: Setting)
}