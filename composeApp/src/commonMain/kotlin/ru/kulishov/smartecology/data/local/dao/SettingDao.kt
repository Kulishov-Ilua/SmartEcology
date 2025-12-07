package ru.kulishov.smartecology.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.kulishov.smartecology.data.local.data.entity.SettingsEntity

@Dao
interface SettingDao {
    @Query("Select * from settings")
    fun getSettings(): Flow<List<SettingsEntity>>

    @Update(onConflict = REPLACE)
    suspend fun updateSettings(settingsEntity: SettingsEntity)

    @Insert
    suspend fun insertSetting(settingsEntity: SettingsEntity)
}