package com.example.screendesign.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.screendesign.repository.Repository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val repository = Repository()
    val empId = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    fun getTopPage(){
        viewModelScope.launch {
            val response = repository.checkServer().text
            Log.d("response", response)
        }
    }

    fun login(){
        viewModelScope.launch {
            val empSnap = empId.value
            val passSnap = password.value
            Log.d("input","${empSnap},${passSnap}")
            if (empSnap.isNullOrBlank() || passSnap.isNullOrBlank()){
                Log.d("null or blank","")
            }else{
                val token = repository.login(
                    empId = empSnap,
                    pass = passSnap
                ).access_token

                Log.d("AccessToken", token)
            }
        }
    }
}