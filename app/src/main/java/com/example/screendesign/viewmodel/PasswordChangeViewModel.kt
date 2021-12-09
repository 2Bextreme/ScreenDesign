package com.example.screendesign.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.example.screendesign.repository.Repository
import kotlinx.coroutines.launch

class PasswordChangeViewModel(
    context: Context
) : AndroidViewModel(context as Application) ,LifecycleObserver{
    private val repository = Repository(context)
    val oldPass = MutableLiveData<String>()
    val newPass1 = MutableLiveData<String>()
    val newPass2 = MutableLiveData<String>()

    var notGoodSizePass1 :Boolean = true
    var notGoodSizePass2 :Boolean = true
    var notMatchPass :Boolean = true

    val isChanging :LiveData<Boolean>
        get() = _isChanging
    private val _isChanging = MutableLiveData<Boolean>()

    val isLog :LiveData<Int>
        get() = _isLog
    private val _isLog = MutableLiveData<Int>()

    fun passwordChange(){
        viewModelScope.launch {
            _isChanging.postValue(true)
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
                        Log.d("log","1")
                        _isLog.postValue(1)
                    }else{
                        Log.d("log","99")
                        _isLog.postValue(99)
                    }
                }else{
                    Log.d("log","3")
                    _isLog.postValue(3)
                }
            }else{
                Log.d("log","2")
                _isLog.postValue(2)
            }
            _isChanging.postValue(false)
        }
    }
}
