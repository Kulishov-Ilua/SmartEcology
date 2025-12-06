package ru.kulishov.smartecology.presentation.ui.adminpanel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.kulishov.smartecology.presentation.ui.camera.BaseViewModel
import ru.kulishov.smartecology.presentation.ui.elements.authorized.AuthorizedBlockViewModel


class AdminPanelViewModel(
    val onAuthAdmin:(String)-> Unit,
    val onAuthUser:(String)-> Unit

): BaseViewModel() {
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
    },{onAuthUser(it)})

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