package com.example.screendesign.ui.configuration

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.screendesign.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConfigurationViewModel(
    context: Context
) : ViewModel() {
    private val repository =Repository(context)

    var notiBoolean  = MutableLiveData<Boolean>()

    fun checkNotificationBoolean(){
        viewModelScope.launch {
            notiBoolean.postValue(repository.getNotification().toBoolean())
        }
    }

    fun setNotificationBoolean(){
        viewModelScope.launch {
            repository.setNotification(
                bool = notiBoolean.value.toString(),
                userId = repository.getUserId()
            )
        }
    }
}