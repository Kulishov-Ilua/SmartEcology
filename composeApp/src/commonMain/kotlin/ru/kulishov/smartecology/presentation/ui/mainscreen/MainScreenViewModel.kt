package ru.kulishov.smartecology.presentation.ui.mainscreen


import androidx.compose.runtime.Composable
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.timeout
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.json.Json
import ru.kulishov.smartecology.data.SystemPrompt
import ru.kulishov.smartecology.data.SystemPrompt2
import ru.kulishov.smartecology.data.remote.model.ChatCompletionResponse
import ru.kulishov.smartecology.presentation.ui.camera.BaseViewModel

class MainScreenViewModel() : BaseViewModel() {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }


    }
    private val _uiState = MutableStateFlow<UiState>(UiState.Success)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _infoState = MutableStateFlow<String>("Факты")
    val infoState: StateFlow<String> = _infoState.asStateFlow()

    private val _inputState = MutableStateFlow<Boolean>(true)
    val inputState: StateFlow<Boolean> = _inputState.asStateFlow()

    private val _orientation = MutableStateFlow<Boolean>(true)
    val orientation: StateFlow<Boolean> = _orientation.asStateFlow()

    private val _activities = MutableStateFlow<List<String>>(listOf("Факты", "Лидерборд"))
    val activities: StateFlow<List<String>> = _activities.asStateFlow()

    private val _activitiesMap = MutableStateFlow<Map<String, String>>(mapOf())
    val activitiesMap: StateFlow<Map<String, String>> = _activitiesMap.asStateFlow()

    var modelAnswer =""


    fun setInputState(state: Boolean) {
        launch {
            _inputState.value = state
        }

    }

    fun setOrientation(state: Boolean) {
        launch {
            _orientation.value = state
        }
    }

    fun setInfoBlock(state: String) {
        _infoState.value = state
    }
    fun imagePrompt(base64Image: String){
        launch {
            println("photo")
            _uiState.value = UiState.Loading

            try {

                val jsonBody = """
                {
                    "model": "ai/gemma3",
                    "messages": [
                        {
                            "role": "system",
                            "content": "$SystemPrompt"
                        },
                        {
                            "role": "user",
                            "content": [
                                {
                                    "type": "text",
                                    "text": "Это пытаются выбросить. Определи что на фотографии и скажи, в какие контейнеры"
                                },
                                {
                                    "type": "image_url",
                                    "image_url": {
                                        "url": "data:image/jpeg;base64,$base64Image"
                                    }
                                }
                            ]
                        }
                    ],
                    "max_tokens": 500,
                    "temperature": 0.1
                }
                """.trimIndent()

                val response: HttpResponse =
                    client.post("http://10.0.2.2:12434/engines/llama.cpp/v1/chat/completions") {
                        contentType(ContentType.Application.Json)
                        setBody(jsonBody)
                        timeout {
                            requestTimeoutMillis = 120000L
                            connectTimeoutMillis = 30000L
                            socketTimeoutMillis = 240000L
                        }
                    }


                val responseText = response.bodyAsText()
                println("Сырой ответ: $responseText")

                val parsedResponse = Json { ignoreUnknownKeys = true }
                    .decodeFromString<ChatCompletionResponse>(responseText)

                println("Status: ${parsedResponse.id}")
                println("Response: ${parsedResponse.choices[0].message}")
                modelAnswer=parsedResponse.choices[0].message.content
            }catch (e: Exception){
                println(e)
            }
            _uiState.value= UiState.Success

        }
    }

    fun textRequest(text: String){

        launch {
            _uiState.value = UiState.Loading

            try {
                val jsonBody = """
        {
            "model": "ai/gemma3",
            "messages": [
                {
                    "role": "system",
                    "content": "$SystemPrompt2"
                },
                {
                    "role": "user",
                    "content": "$text"
                }
            ]
        }
        """.trimIndent()

                val response: HttpResponse =
                    client.post("http://10.0.2.2:12434/engines/llama.cpp/v1/chat/completions") {
                        contentType(ContentType.Application.Json)
                        setBody(jsonBody)
                        timeout {
                            requestTimeoutMillis = 120000L
                            connectTimeoutMillis = 30000L
                            socketTimeoutMillis = 240000L
                        }
                    }


                val responseText = response.bodyAsText()
                println("Сырой ответ: $responseText")

                val parsedResponse = Json { ignoreUnknownKeys = true }
                    .decodeFromString<ChatCompletionResponse>(responseText)

                println("Status: ${parsedResponse.id}")
                println("Response: ${parsedResponse.choices[0].message}")
                modelAnswer=parsedResponse.choices[0].message.content
            }catch (e: Exception){
                println(e)
            }
            _uiState.value= UiState.Success

        }
    }

    sealed class UiState {
        object Accept: UiState()
        object Result: UiState()
        object Loading : UiState()
        object Success : UiState()
        data class Error(val message: String) : UiState()
    }
}