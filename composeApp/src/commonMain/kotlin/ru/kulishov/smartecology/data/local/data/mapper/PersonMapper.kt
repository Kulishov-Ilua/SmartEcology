package ru.kulishov.smartecology.data.local.data.mapper

import ru.kulishov.smartecology.data.local.data.entity.PersonEntity
import ru.kulishov.smartecology.domain.model.Person

object PersonMapper {
    fun toDomain(entity: PersonEntity): Person = Person(
        id = entity.id,
        name =entity.name,
        icon = entity.icon,
        password = entity.password,
        score = entity.score,
        statistic = entity.statistic,
    )


    fun toEntity(domain: Person): PersonEntity = PersonEntity(
        id = domain.id,
        name = domain.name,
        icon = domain.icon,
        password = domain.password,
        score = domain.score,
        statistic = domain.statistic,
    )
}