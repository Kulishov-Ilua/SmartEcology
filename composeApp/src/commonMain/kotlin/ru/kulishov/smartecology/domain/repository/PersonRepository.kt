package ru.kulishov.smartecology.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.kulishov.smartecology.domain.model.Person

interface PersonRepository {
    fun getPersons(): Flow<List<Person>>
    suspend fun setPerson(person: Person)
    suspend fun addPerson(person: Person)
    suspend fun deletePerson(person: Person)
}