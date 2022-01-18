package com.example.screendesign.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.*
import com.example.screendesign.activity.TopPageActivity
import com.example.screendesign.data.ShiftDate
import com.example.screendesign.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class DesireShiftHandOverVerificationViewModel(context: Context) : ViewModel() {
    var shiftList = ArrayList<ShiftDate>()
    private val repository = Repository(context)

    val isLog : LiveData<Int>
        get() = _isLog
    private val _isLog = MutableLiveData<Int>()

    fun ShiftHandOver(){
        viewModelScope.launch {
            _isLog.postValue(98)
            val year = shiftList.get(index = 0).year
            val month = shiftList.get(index = 0).month
            val days = ArrayList<Int>()
            shiftList.forEach {
                days.add(it.day.toInt())
            }
            Log.d("log","log")
//            val ret = repository.deleteShift()
//            Log.d("シフト削除",ret.toString())
            val result = repository.shiftHandOver(
                year = year.toInt(),
                month = month.toInt(),
                days = days.toList()
            ).result
            Log.d("result",result.toString())
            Log.d("shiftList", "${year}/${month}/${days}")
            if (result){
                _isLog.postValue(1)
            }
            _isLog.postValue(2)
        }
    }
}