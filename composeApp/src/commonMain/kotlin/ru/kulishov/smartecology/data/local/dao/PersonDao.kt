package ru.kulishov.smartecology.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.kulishov.smartecology.data.local.data.entity.PersonEntity
import ru.kulishov.smartecology.domain.model.Person

@Dao
interface PersonDao {
    @Query("Select * from person")
    fun  getPersons(): Flow<List<PersonEntity>>

    @Insert
    suspend fun addPerson(person: PersonEntity)

    @Delete
   suspend fun deletePerson(person: PersonEntity)

    @Update
    suspend fun updatePerson(person: PersonEntity)
}