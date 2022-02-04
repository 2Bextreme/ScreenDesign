package com.example.screendesign.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.example.screendesign.repository.Repository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PasswordChangeViewModel(
    context: Context
) :ViewModel() {
    private val repository = Repository(context)
    val oldPass = MutableLiveData<String>()
    val newPass1 = MutableLiveData<String>()
    val newPass2 = MutableLiveData<String>()

    var notGoodSizePass1 :Boolean = true
    var notGoodSizePass2 :Boolean = true
    var notMatchPass :Boolean = true

    val isLog :MutableLiveData<Int>
        get() = _isLog
    private val _isLog = MutableLiveData<Int>()

    fun passwordChange(){
        viewModelScope.launch {
            _isLog.postValue(98)
            val pass1snap = newPass1.value
            val oldPassSnap = oldPass.value
            Log.d("result",pass1snap.toString())
            Log.d("result",oldPassSnap.toString())
            if (notMatchPass){
                if (oldPassSnap != null){
                    val ret = repository.changePassword(
                        newPass = pass1snap!!,
                        oldPass = oldPassSnap
                    ).result
                    Log.d("result",ret.toString())
                    if (ret){
                        _isLog.postValue(1)
                    }else{
                        _isLog.postValue(99)
                    }
                }else{
                    _isLog.postValue(3)
                }
            }else{
                _isLog.postValue(2)
            }
        }
    }
}
