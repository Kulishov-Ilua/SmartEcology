package ru.kulishov.smartecology.data.local.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.kulishov.smartecology.data.local.dao.PersonDao
import ru.kulishov.smartecology.data.local.data.mapper.PersonMapper
import ru.kulishov.smartecology.domain.model.Person
import ru.kulishov.smartecology.domain.repository.PersonRepository

class PersonRepositoryImpl(
    private val personDao: PersonDao
): PersonRepository {
    override fun getPersons(): Flow<List<Person>> = personDao.getPersons().map { entity-> entity.map { PersonMapper.toDomain(it) } }

    override suspend fun setPerson(person: Person) {
        personDao.updatePerson(PersonMapper.toEntity(person))
    }

    override suspend fun addPerson(person: Person) {
        personDao.addPerson(PersonMapper.toEntity(person))
    }

    override suspend fun deletePerson(person: Person) {
        personDao.deletePerson(PersonMapper.toEntity(person))
    }

}