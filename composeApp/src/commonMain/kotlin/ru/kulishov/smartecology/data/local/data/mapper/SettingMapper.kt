import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.kulishov.smartecology.data.local.data.entity.SettingsEntity
import ru.kulishov.smartecology.domain.model.QuizeGame
import ru.kulishov.smartecology.domain.model.Setting
import ru.kulishov.smartecology.domain.model.StartQuize
import ru.kulishov.smartecology.domain.model.TrashBox

object SettingsMapper {
    private val gson = Gson()

    fun toDomain(entity: SettingsEntity): Setting {
        return Setting(
            id = entity.id,
            quizeState = entity.quizeState,
            textState = entity.textState,
            imageState = entity.imageState,
            factsState = entity.factsState,
            topListState = entity.topListState,
            quizeGameState = entity.quizeGameState,
            smartStatisticState = entity.smartStatisticState,
            startQuize = parseStartQuize(entity.startQuize),
            facts = parseFacts(entity.facts),
            quizeGame = parseQuizeGame(entity.quizeGame),
            boxes = parseBoxes(entity.boxes),
            activities = parseFacts(entity.facts),
            persons = parsePersons(entity.persons)
        )
    }

    fun toEntity(domain: Setting): SettingsEntity {
        return SettingsEntity(
            id = domain.id,
            quizeState = domain.quizeState,
            textState = domain.textState,
            imageState = domain.imageState,
            factsState = domain.factsState,
            topListState = domain.topListState,
            quizeGameState = domain.quizeGameState,
            smartStatisticState = domain.smartStatisticState,
            startQuize = serializeStartQuize(domain.startQuize),
            facts = serializeFacts(domain.facts),
            quizeGame = serializeQuizeGame(domain.quizeGame),
            boxes = serializeBoxes(domain.boxes),
            activities = serializeFacts(domain.activities),
            persons = serializePersons(domain.persons)
        )
    }

    private fun parseStartQuize(json: String): List<StartQuize> {
        return try {
            if (json.isBlank()) return emptyList()
            val type = object : TypeToken<List<StartQuize>>() {}.type
            gson.fromJson(json, type) ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    private fun parseFacts(json: String): List<String> {
        return try {
            if (json.isBlank()) return emptyList()
            val type = object : TypeToken<List<String>>() {}.type
            gson.fromJson(json, type) ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    private fun parseQuizeGame(json: String): List<QuizeGame> {
        return try {
            if (json.isBlank()) return emptyList()
            val type = object : TypeToken<List<QuizeGame>>() {}.type
            gson.fromJson(json, type) ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    private fun parseBoxes(json: String): List<TrashBox> {
        return try {
            if (json.isBlank()) return emptyList()
            val type = object : TypeToken<List<TrashBox>>() {}.type
            gson.fromJson(json, type) ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    private fun parsePersons(json: String): List<Int> {
        return try {
            if (json.isBlank()) return emptyList()
            val type = object : TypeToken<List<Int>>() {}.type
            gson.fromJson(json, type) ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }


    private fun serializeStartQuize(list: List<StartQuize>): String {
        return try {
            gson.toJson(list)
        } catch (e: Exception) {
            "[]"
        }
    }

    private fun serializeFacts(list: List<String>): String {
        return try {
            gson.toJson(list)
        } catch (e: Exception) {
            "[]"
        }
    }

    private fun serializeQuizeGame(list: List<QuizeGame>): String {
        return try {
            gson.toJson(list)
        } catch (e: Exception) {
            "[]"
        }
    }

    private fun serializeBoxes(list: List<TrashBox>): String {
        return try {
            gson.toJson(list)
        } catch (e: Exception) {
            "[]"
        }
    }

    private fun serializePersons(list: List<Int>): String {
        return try {
            gson.toJson(list)
        } catch (e: Exception) {
            "[]"
        }
    }
}
