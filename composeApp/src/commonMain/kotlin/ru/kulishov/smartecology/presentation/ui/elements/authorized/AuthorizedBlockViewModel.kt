package ru.kulishov.smartecology.presentation.ui.elements.authorized

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.kulishov.smartecology.domain.model.Person
import ru.kulishov.smartecology.presentation.ui.camera.BaseViewModel

class AuthorizedBlockViewModel ( val nameInp:String,val onGood:()-> Unit,
    val onAuthAdmin:(String)-> Unit, val onAuthUser:(String)-> Unit,
    val onCheckUser:(Person)->Unit, val onAddUser:(Person)-> Unit): BaseViewModel(){
    private val _uiState = MutableStateFlow<UiState>(UiState.AdminEmpty)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _password = MutableStateFlow<String>("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _passwordCheck = MutableStateFlow<String>("")
    val passwordCheck: StateFlow<String> = _passwordCheck.asStateFlow()

    private val _secondPassword = MutableStateFlow<String>("")
    val secondPassword: StateFlow<String> = _secondPassword.asStateFlow()

    val _users = MutableStateFlow<List<Person>>(emptyList())
    val users: StateFlow<List<Person>> =  _users.asStateFlow()

    fun setUsers(users:List<Person>){
        _users.value=users
    }




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
        _password.value=""
        _secondPassword.value=""
    }

    fun checkUser(){
        if(_name.value!=""){
            onAddUser(Person(0,name.value,0,"",0,""))
        }else{
            _isError.value=true
        }
        _name.value=""
    }

    fun setName(state:String){
        _name.value=state
    }
    fun checkPassword(){
        if(password.value==passwordCheck.value&&password.value!=""){
            onGood()
        }else{
            _isError.value=true

        }
        _password.value=""
    }

    fun setState(state: UiState){
        _uiState.value=state
    }

    sealed class UiState {
        object AdminEmpty : UiState()
        object Admin: UiState()
        object UserEmpty : UiState()
        object User : UiState()
        object UserList : UiState()

        data class Error(val message: String) : UiState()
    }
}