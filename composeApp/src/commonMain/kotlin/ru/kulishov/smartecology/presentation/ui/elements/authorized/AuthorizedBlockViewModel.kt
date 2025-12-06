package ru.kulishov.smartecology.presentation.ui.elements.authorized

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.kulishov.smartecology.presentation.ui.camera.BaseViewModel

class AuthorizedBlockViewModel ( val nameInp:String,val onGood:()-> Unit,
    val onAuthAdmin:(String)-> Unit, val onAuthUser:(String)-> Unit): BaseViewModel(){
    private val _uiState = MutableStateFlow<UiState>(UiState.AdminEmpty)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _password = MutableStateFlow<String>("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _passwordCheck = MutableStateFlow<String>("")
    val passwordCheck: StateFlow<String> = _passwordCheck.asStateFlow()

    private val _secondPassword = MutableStateFlow<String>("")
    val secondPassword: StateFlow<String> = _secondPassword.asStateFlow()






    private val _name = MutableStateFlow<String>("")
    val name: StateFlow<String> = _name.asStateFlow()
    fun setPasswordCheck(state:String){
        _passwordCheck.value=state
        _uiState.value= UiState.Admin
    }

    private val _isError = MutableStateFlow<Boolean>(false)
    val isError: StateFlow<Boolean> = _isError.asStateFlow()

    fun setPassword(state:String){
        _password.value=state

    }
    fun setSecondPassword(state: String){
        _secondPassword.value=state
    }

    fun checkSecondPassword(){
        if(secondPassword.value==password.value&&password.value!=""){
            onAuthAdmin(password.value)
            _uiState.value= UiState.Admin
        }else{
            _isError.value=true
        }
    }

    fun checkAuthUser(){
        if(_name.value!=""){
            onAuthUser(name.value)
        }else{
            _isError.value=true
        }
    }

    fun setName(state:String){
        _name.value=state
    }
    fun checkPassword(){
        if(password.value==passwordCheck.value&&password.value!=""){
            onGood()
        }else{
            _isError.value=true
            _password.value=""
        }
    }

    fun setState(state: UiState){
        _uiState.value=state
    }

    sealed class UiState {
        object AdminEmpty : UiState()
        object Admin: UiState()
        object UserEmpty : UiState()
        object User : UiState()

        data class Error(val message: String) : UiState()
    }
}