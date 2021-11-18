package com.example.screendesign.viewmodel

import android.content.Context
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.screendesign.activity.LoginActivity
import com.example.screendesign.data.LoginFormState
import com.example.screendesign.fragment.LoginFragment
import com.example.screendesign.repository.Repository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.security.auth.callback.Callback

class LoginViewModel(
    context: Context
) : ViewModel() {
    private val repository = Repository(context)
    val empId = MutableLiveData<String>()
//    val empId : LiveData<String> = _empId
    val password = MutableLiveData<String>()
//    val password :LiveData<String> = _password
    var validLogin:Boolean = false

    interface Callback{
        fun moveTopPage()
        fun toast()
    }

    private val _loginState = MutableLiveData<LoginFormState>()
    val loginState :LiveData<LoginFormState> = _loginState

    suspend fun accessTokenGet():String{
        return repository.get()
    }

//    fun getTopPage(){
//        viewModelScope.launch {
//            val response = repository.checkServer().text
//            Log.d("response", response)
//        }
//    }

    fun login(callback: Callback){
         viewModelScope.launch {
            Log.d("input","${empId.value},${password.value}")
            val token:String? = repository.login(
                empId = empId.value!!,
                pass = password.value!!
            ).access_token
            if (token != null){
                validLogin = true
                repository.set(token)
                callback.moveTopPage()
                Log.d("AccessToken", token)
            }else{
                validLogin = false
                Log.d("AccessToken", "null")
                callback.toast()
            }
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginState.value = LoginFormState(usernameError = "無効なユーザ名です")
        } else if (!isPasswordValid(password)) {
            _loginState.value = LoginFormState(passwordError = "パスワードが5文字以下です")
        } else {
            _loginState.value = LoginFormState(isDataValid = true)
        }
    }

    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}