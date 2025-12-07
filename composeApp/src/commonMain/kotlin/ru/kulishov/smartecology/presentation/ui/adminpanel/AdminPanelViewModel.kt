package ru.kulishov.smartecology.presentation.ui.adminpanel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.kulishov.smartecology.domain.model.QuizeGame
import ru.kulishov.smartecology.domain.model.Setting
import ru.kulishov.smartecology.domain.model.StartQuize
import ru.kulishov.smartecology.domain.model.TrashBox
import ru.kulishov.smartecology.presentation.ui.camera.BaseViewModel
import ru.kulishov.smartecology.presentation.ui.elements.authorized.AuthorizedBlockViewModel


class AdminPanelViewModel(
    val onAuthAdmin:(String)-> Unit,
    val onAuthUser:(String)-> Unit,
    val onSaveSetting:(Setting)-> Unit

): BaseViewModel() {

    private val _cameraAccept = MutableStateFlow<Boolean>(false)
    val cameraAccept: StateFlow<Boolean> = _cameraAccept.asStateFlow()

    fun setCamera(state: Boolean){
        _cameraAccept.value=state
    }

    fun saveSetting(setting: Setting){
        onSaveSetting(setting)
    }
    private val _textInpAccept = MutableStateFlow<Boolean>(false)
    val textInpAccept: StateFlow<Boolean> = _textInpAccept.asStateFlow()

    fun setTextInp(state: Boolean){
        _textInpAccept.value=state
    }
    private val _quizeInpAccept = MutableStateFlow<Boolean>(false)
    val quizetextInpAccept: StateFlow<Boolean> = _quizeInpAccept.asStateFlow()

    fun setQuizeState(state: Boolean){
        _quizeInpAccept.value=state
    }


    private val _factAccept = MutableStateFlow<Boolean>(false)
    val factAccept: StateFlow<Boolean> = _factAccept.asStateFlow()

    fun setFactState(state: Boolean){
        _factAccept.value=state
    }

    private val _topListState = MutableStateFlow<Boolean>(false)
    val topListState: StateFlow<Boolean> = _topListState.asStateFlow()

    fun setTopAccept(state: Boolean){
        _topListState.value=state
    }

    private val _quizeGameState = MutableStateFlow<Boolean>(false)
    val quizeGameState: StateFlow<Boolean> = _quizeGameState.asStateFlow()

    fun setQuizeGameState(state: Boolean){
        _quizeGameState.value=state
    }

    private val _quizeGame = MutableStateFlow<List<QuizeGame>>(emptyList())
    val quizeGame: StateFlow<List<QuizeGame>> = _quizeGame.asStateFlow()

    fun setQuizeGame(state: List<QuizeGame>){
        _quizeGame.value=state
    }

    private val _activities = MutableStateFlow<List<String>>(listOf())
    val activities: StateFlow<List<String>> = _activities.asStateFlow()

    fun setActivities(state: List<String>){
        _activities.value=state
    }

    private val _boxes = MutableStateFlow<List<TrashBox>>(emptyList())
    val boxes: StateFlow<List<TrashBox>> = _boxes.asStateFlow()

    fun setBoxes(state: List<TrashBox>){
       _boxes.value=state
    }








    private val _inputState = MutableStateFlow<Int>(0)
    val inputState: StateFlow<Int> = _inputState.asStateFlow()

    private val _facts = MutableStateFlow<List<String>>(emptyList())
    val facts: StateFlow<List<String>> = _facts.asStateFlow()



    private val _startQuize = MutableStateFlow<List<StartQuize>>(emptyList())
    val startQuize: StateFlow<List<StartQuize>> = _startQuize.asStateFlow()

    fun setData(activities:List<String>, factAccept: Boolean, facts: List<String>, topAccept: Boolean, quizeAccept: Boolean,
                cameraAccept: Boolean, textInnAccept: Boolean, quizeStartAccept: Boolean, boxes:List<TrashBox>,quizeGame: List<QuizeGame>){
        _cameraAccept.value=cameraAccept
        _quizeInpAccept.value=quizeAccept
        _textInpAccept.value=textInnAccept

        _topListState.value=topAccept
        _factAccept.value=factAccept
        _quizeGameState.value=quizeStartAccept

        _facts.value=facts
        _quizeGame.value=quizeGame
        _activities.value=activities
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