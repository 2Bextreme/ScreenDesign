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
import java.util.*
import kotlin.collections.ArrayList

class DesireShiftHandOverVerificationViewModel(context: Context) : ViewModel() {
    var shiftList = ArrayList<ShiftDate>()
    private val repository = Repository(context)
    private var year = 9999
    private var month = 99

    val isLog : LiveData<Int>
        get() = _isLog
    private val _isLog = MutableLiveData<Int>()

    fun ShiftHandOver(){
        viewModelScope.launch {
            _isLog.postValue(98)

            repository.deleteShift()

            val calendar = Calendar.getInstance()
            month = calendar.get(Calendar.MONTH) + 2
            if(month == 13){
                month = 1
                year  = calendar.get(Calendar.YEAR) + 1
            }else{
                year  = calendar.get(Calendar.YEAR)
            }
            calendar.set(Calendar.YEAR,year)
            calendar.set(Calendar.MONTH,month-1)
            val days = ArrayList<Int>()
            val notHopeDay = ArrayList<Int>()
            shiftList.forEach {
                notHopeDay.add(it.day.toInt())
            }

            val dayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
            for (day in 1..dayOfMonth){
                if (!notHopeDay.contains(day)){
                    days.add(day)
                }
            }


            Log.d("year",year.toString())
            Log.d("month",month.toString())
            Log.d("days",days.toString())
            val result = repository.shiftHandOver(
                year = year,
                month = month,
                days = days.toList()
            ).result
            Log.d("result",result.toString())
            if (result){
                _isLog.postValue(1)
            }else{
                _isLog.postValue(2)
            }
        }
    }
}