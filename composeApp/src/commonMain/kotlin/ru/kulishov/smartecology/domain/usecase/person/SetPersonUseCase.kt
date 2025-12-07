package ru.kulishov.smartecology.domain.usecase.person

import ru.kulishov.smartecology.domain.model.Person
import ru.kulishov.smartecology.domain.model.Setting
import ru.kulishov.smartecology.domain.repository.PersonRepository

class SetPersonUseCase(
    private val repository: PersonRepository
) {
    suspend operator fun invoke(person: Person) = repository.setPerson(person)
}