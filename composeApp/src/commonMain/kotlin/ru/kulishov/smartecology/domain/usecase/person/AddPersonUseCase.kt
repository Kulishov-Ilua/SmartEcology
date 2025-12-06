package ru.kulishov.smartecology.domain.usecase.person

import ru.kulishov.smartecology.domain.model.Person
import ru.kulishov.smartecology.domain.repository.PersonRepository

class AddPersonUseCase(
    private val repository: PersonRepository
) {
    suspend operator fun invoke(person: Person) = repository.addPerson(person)
}