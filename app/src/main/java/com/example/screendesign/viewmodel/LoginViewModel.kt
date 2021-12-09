package com.example.screendesign.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.screendesign.R
import com.example.screendesign.activity.LoginActivity
import com.example.screendesign.activity.TopPageActivity
import com.example.screendesign.data.LoginFormState
import com.example.screendesign.fragment.LoginFragment
import com.example.screendesign.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.security.auth.callback.Callback

class LoginViewModel(
    context: Context
) : ViewModel() {
    private val repository = Repository(context)
    val empId = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    var validLogin:Boolean = false

    val isLog :LiveData<Int>
        get() = _isLog
    private val _isLog = MutableLiveData<Int>()

    val loginCheck : LiveData<Boolean>
        get() = _loginCheck
    private val _loginCheck = MutableLiveData<Boolean>()


    interface Callback{
        fun moveTopPage()
        fun toast()
    }

    private val _loginState = MutableLiveData<LoginFormState>()
    val loginState :LiveData<LoginFormState> = _loginState

    fun loginCheck(){
        viewModelScope.launch {
            _loginCheck.postValue(true)
            val accessToken = repository.get()
            if (accessToken != "null"){
                Log.d("Login","すでにログインしています")
                Log.d("AccessToken",accessToken)
                if (repository.accessTokenCheck(accessToken).is_valid){
                    Log.d("AccessToken","アクセストークンは有効です")
                    _isLog.postValue(1)
                }else{
                    Log.d("AccessToken","アクセストークンが失効しています")
                    _isLog.postValue(0)
                }
            }else{
                _isLog.postValue(0)
                Log.d("Login","ログインしていません")
            }
            _loginCheck.postValue(false)
        }
    }


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