package ru.kulishov.smartecology.presentation.ui.adminpanel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.kulishov.smartecology.domain.model.QuizeGame
import ru.kulishov.smartecology.domain.model.StartQuize
import ru.kulishov.smartecology.domain.model.TrashBox
import ru.kulishov.smartecology.presentation.ui.camera.BaseViewModel
import ru.kulishov.smartecology.presentation.ui.elements.authorized.AuthorizedBlockViewModel


class AdminPanelViewModel(
    val onAuthAdmin:(String)-> Unit,
    val onAuthUser:(String)-> Unit

): BaseViewModel() {
    private val _activities = MutableStateFlow<List<String>>(listOf("Факты", "Лидерборд"))
    val activities: StateFlow<List<String>> = _activities.asStateFlow()

    private val _factAccept = MutableStateFlow<Boolean>(false)
    val factAccept: StateFlow<Boolean> = _factAccept.asStateFlow()

    private val _topListState = MutableStateFlow<Boolean>(false)
    val topListState: StateFlow<Boolean> = _topListState.asStateFlow()

    private val _quizeGameState = MutableStateFlow<Boolean>(false)
    val quizeGameState: StateFlow<Boolean> = _quizeGameState.asStateFlow()

    private val _quizeGame = MutableStateFlow<List<QuizeGame>>(emptyList())
    val quizeGame: StateFlow<List<QuizeGame>> = _quizeGame.asStateFlow()



    private val _inputState = MutableStateFlow<Int>(0)
    val inputState: StateFlow<Int> = _inputState.asStateFlow()

    private val _facts = MutableStateFlow<List<String>>(emptyList())
    val facts: StateFlow<List<String>> = _facts.asStateFlow()

    private val _cameraAccept = MutableStateFlow<Boolean>(false)
    val cameraAccept: StateFlow<Boolean> = _cameraAccept.asStateFlow()
    private val _textInpAccept = MutableStateFlow<Boolean>(false)
    val textInpAccept: StateFlow<Boolean> = _textInpAccept.asStateFlow()

    private val _quizeInpAccept = MutableStateFlow<Boolean>(false)
    val quizetextInpAccept: StateFlow<Boolean> = _quizeInpAccept.asStateFlow()

    private val _startQuize = MutableStateFlow<List<StartQuize>>(emptyList())
    val startQuize: StateFlow<List<StartQuize>> = _startQuize.asStateFlow()

    fun setData(activities:List<String>, factAccept: Boolean, facts: List<String>, topAccept: Boolean, quizeAccept: Boolean,
                cameraAccept: Boolean, textInnAccept: Boolean, quizeStartAccept: Boolean, boxes:List<TrashBox>){
        _factAccept.value=factAccept
        _facts.value=facts

    }




    private val _uiState = MutableStateFlow<UiState>(UiState.Success)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _password = MutableStateFlow<String>("")
    val password: StateFlow<String> = _password.asStateFlow()
    fun setPassword(state:String){
        _password.value=state
        authorizedBlockViewModel.setPasswordCheck(password.value)
        if(password.value==""){
            authorizedBlockViewModel.setState(AuthorizedBlockViewModel.UiState.AdminEmpty)
            _uiState.value= UiState.NotPassword
        }else{
            authorizedBlockViewModel.setState(AuthorizedBlockViewModel.UiState.Admin)
            _uiState.value= UiState.UnAuthorized
        }
    }
    var nameUser="Администратор"

    val authorizedBlockViewModel = AuthorizedBlockViewModel(nameUser,{
        auth()
    },{
        onAuthAdmin(it)
    },{onAuthUser(it)},
        {

        },{

        })

    init {
        if(password.value==""){
            authorizedBlockViewModel.setState(AuthorizedBlockViewModel.UiState.AdminEmpty)
            _uiState.value= UiState.NotPassword
        }else{
            authorizedBlockViewModel.setState(AuthorizedBlockViewModel.UiState.Admin)
            _uiState.value= UiState.UnAuthorized
        }
    }

    fun authAdmin(password: String){
        onAuthAdmin(password)
        _uiState.value= UiState.UnAuthorized
    }

    fun auth(){
        _uiState.value= UiState.Success
    }
    fun unAuth(){
        _uiState.value= UiState.UnAuthorized
    }
    sealed class UiState {

        object UnAuthorized : UiState()
        object NotPassword: UiState()
        object Success : UiState()

        data class Error(val message: String) : UiState()
    }
}