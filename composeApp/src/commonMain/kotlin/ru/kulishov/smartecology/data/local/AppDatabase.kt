package ru.kulishov.smartecology.data.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import ru.kulishov.smartecology.data.local.dao.PersonDao
import ru.kulishov.smartecology.data.local.dao.SettingDao
import ru.kulishov.smartecology.data.local.data.entity.PersonEntity
import ru.kulishov.smartecology.data.local.data.entity.SettingsEntity
import kotlin.concurrent.Volatile


@Database(
    entities = [SettingsEntity::class,
        PersonEntity::class], version = 1
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun settingDao(): SettingDao
    abstract fun personDao(): PersonDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(builder:RoomDatabase.Builder<AppDatabase>): AppDatabase {
            if(INSTANCE==null){
                INSTANCE=getRoomDatabase(builder)
            }
            return INSTANCE!!
        }


    }
}

@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}

fun getRoomDatabase(
    builder: RoomDatabase.Builder<AppDatabase>
): AppDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}