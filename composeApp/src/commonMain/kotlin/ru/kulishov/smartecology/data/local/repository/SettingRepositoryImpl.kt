package ru.kulishov.smartecology.data.local.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.kulishov.smartecology.data.local.dao.SettingDao
import ru.kulishov.smartecology.domain.model.Setting
import ru.kulishov.smartecology.domain.repository.SettingRepository

class SettingRepositoryImpl(
    private val settingDao: SettingDao
): SettingRepository {
    override fun getSettings(): Flow<List<Setting>> = settingDao.getSettings().map { entities -> entities.map {  it -> SettingsMapper.toDomain(it) }}

    override suspend fun setSetting(setting: Setting) {
        settingDao.updateSettings(SettingsMapper.toEntity(setting))
    }

    override suspend fun insertSetting(setting: Setting) {
        settingDao.insertSetting(SettingsMapper.toEntity(setting))
    }
}