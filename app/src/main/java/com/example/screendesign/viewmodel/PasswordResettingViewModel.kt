package com.example.screendesign.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.screendesign.repository.Repository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PasswordResettingViewModel (
    context: Context
) : ViewModel() {
    private val repository = Repository(context)
    val empId = MutableLiveData<String>()
    val mailAddress = MutableLiveData<String>()

    val isLog : MutableLiveData<Int>
        get() = _isLog
    private val _isLog = MutableLiveData<Int>()

    fun resetPass(){
        viewModelScope.launch {
            _isLog.postValue(1)
            Log.d("id",empId.value!!)
            Log.d("mailAddress",mailAddress.value!!)
            val ret = repository.forgetPassword(
                empId.value!!,
                mailAddress.value!!
            ).result
            if (ret){
                _isLog.postValue(0)
            }else{
                _isLog.postValue(2)
            }

        }
    }
}