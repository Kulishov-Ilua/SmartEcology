package ru.kulishov.smartecology.domain.usecase.person

import kotlinx.coroutines.flow.Flow
import ru.kulishov.smartecology.domain.model.Person
import ru.kulishov.smartecology.domain.repository.PersonRepository

class GetPersonUseCase(
    private val repository: PersonRepository
) {
    operator fun invoke(): Flow<List<Person>> = repository.getPersons()
}