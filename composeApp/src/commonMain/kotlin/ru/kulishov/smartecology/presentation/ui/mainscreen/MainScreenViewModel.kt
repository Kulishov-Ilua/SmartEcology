package ru.kulishov.smartecology.presentation.ui.mainscreen


import androidx.room.RoomDatabase
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
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
import kotlinx.coroutines.flow.catch
import kotlinx.serialization.json.Json
import ru.kulishov.smartecology.data.SystemPrompt
import ru.kulishov.smartecology.data.local.AppDatabase
import ru.kulishov.smartecology.data.local.getRoomDatabase
import ru.kulishov.smartecology.data.local.repository.SettingRepositoryImpl
import ru.kulishov.smartecology.data.questionListData
import ru.kulishov.smartecology.data.remote.model.ChatCompletionResponse
import ru.kulishov.smartecology.domain.model.Person
import ru.kulishov.smartecology.domain.model.QuizeGame
import ru.kulishov.smartecology.domain.model.Setting
import ru.kulishov.smartecology.domain.model.StartQuize
import ru.kulishov.smartecology.domain.model.TrashBox
import ru.kulishov.smartecology.domain.usecase.person.AddPersonUseCase
import ru.kulishov.smartecology.domain.usecase.settings.GetSettingsUseCase
import ru.kulishov.smartecology.domain.usecase.settings.InsertSettingUseCase
import ru.kulishov.smartecology.domain.usecase.settings.SetSettingsUseCase
import ru.kulishov.smartecology.presentation.ui.adminpanel.AdminPanel
import ru.kulishov.smartecology.presentation.ui.adminpanel.AdminPanelViewModel
import ru.kulishov.smartecology.presentation.ui.camera.BaseViewModel
import ru.kulishov.smartecology.presentation.ui.mainscreenblocs.contentblock.ContentBlockViewModel
import ru.kulishov.smartecology.presentation.ui.mainscreenblocs.inputblock.InputBlockViewModel

class MainScreenViewModel(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val insertSettingsUseCase: InsertSettingUseCase,
    private val setSettingsUseCase: SetSettingsUseCase,
    private val addPersonUseCase: AddPersonUseCase
) : BaseViewModel() {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }


    }


    private val _settings = MutableStateFlow<Setting>(Setting(-1,"",false,false,false,false,false,false,false,
        emptyList(),emptyList(),emptyList(),emptyList(),emptyList(),emptyList()))
    val settings: StateFlow<Setting> = _settings.asStateFlow()

    private val _uiState = MutableStateFlow<UiState>(UiState.Success)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()






    private val _staticticState = MutableStateFlow<Boolean>(false)
    val staticticState: StateFlow<Boolean> = _staticticState.asStateFlow()



    private val _facts = MutableStateFlow<List<String>>(emptyList())
    val facts: StateFlow<List<String>> = _facts.asStateFlow()



    private val _boxes = MutableStateFlow<List<TrashBox>>(emptyList())
    val boxes: StateFlow<List<TrashBox>> = _boxes.asStateFlow()





    private val _orientation = MutableStateFlow<Boolean>(true)
    val orientation: StateFlow<Boolean> = _orientation.asStateFlow()

    var adminPanelViewModel = AdminPanelViewModel({
        authAdmin(it)
    }, {
        authUser(it)
    })
    var modelAnswer =""



    val inputBlockViewModel = InputBlockViewModel(
        onTextPrompt = {
            textRequest(it)
        }, onImagePrompt = {
            imagePrompt(it)
        }, onAnswer = {
            modelAnswer=it
            _uiState.value= UiState.Result
        })
    val contentBlockViewModel = ContentBlockViewModel()



    init {
        //_uiState.value= UiState.Loading
        launch {
//            insertSettingsUseCase(Setting(0,"",true,true,true,true,true,true,true,
//                questionListData.map { StartQuize(it.name,it.good,it.bad,it.output) },emptyList(),emptyList(),emptyList(),emptyList(),emptyList()))

            getSettingsUseCase().catch { e ->
                _uiState.emit(UiState.Error(e.message ?: "Unknow error"))
            }.collect { set ->

                if(set.isEmpty()){
                    insertSettingsUseCase(Setting(0,"",true,true,true,true,true,true,true,emptyList(),emptyList(),emptyList(),emptyList(),listOf("Факты", "Лидерборд"),emptyList()))
                }

                _settings.value=set[0]

                _facts.value=settings.value.facts
                _boxes.value=settings.value.boxes

                inputBlockViewModel.setData(
                    settings.value.imageState,
                    settings.value.textState,
                    settings.value.quizeState
                )
                contentBlockViewModel.setData(
                    settings.value.activities,
                    factAccept = settings.value.factsState,
                    topAccept = settings.value.topListState,
                    quizeAccept = settings.value.quizeGameState,
                    quizeGame = settings.value.quizeGame
                )
                adminPanelViewModel.setPassword(settings.value.password)

                println(settings.value)
            }
            //_uiState.value= UiState.Success
        }

    }


    fun authAdmin(password: String){
        launch {
            _settings.value.password=password
            println(settings.value)
           setSettingsUseCase(settings.value)
        }
    }

    fun authUser(name: String){
        launch {
            addPersonUseCase(Person(0,name,0,"",0,""))
        }
    }
    fun setOrientation(state: Boolean) {
        launch {
            _orientation.value = state
        }
    }



    fun setState(state: UiState){
        _uiState.value=state
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
                            "content": "Ты агент помощник"
                        },
                        {
                            "role": "user",
                            "content": [
                                {
                                    "type": "text",
                                    "text": "Что находится на фотогрфии"
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
                _uiState.value= UiState.Accept
            }catch (e: Exception){
                println(e)
            }


        }
    }
    fun escapeForJson(input: String): String {
        return input
            .replace("\\", "\\\\")
            .replace("\"", "\\\"")
            .replace("\n", "\\n")
            .replace("\r", "\\r")
            .replace("\t", "\\t")
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
                        "content": "${escapeForJson(SystemPrompt)}"
                    },
                    {
                        "role": "user",
                        "content": "${escapeForJson(text)}"
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
                _uiState.value= UiState.Result
            }catch (e: Exception){
                println(e)
            }

        }
    }

    sealed class UiState {
        object Accept: UiState()
        object Result: UiState()
        object Loading : UiState()
        object Success : UiState()
        object WebView: UiState()
        object AdminPanel: UiState()
        data class Error(val message: String) : UiState()
    }

}

