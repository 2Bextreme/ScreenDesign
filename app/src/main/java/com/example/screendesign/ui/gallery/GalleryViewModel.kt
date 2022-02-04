package com.example.screendesign.ui.gallery

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.screendesign.data.ShiftList
import com.example.screendesign.data.ToMonthShitList
import com.example.screendesign.repository.Repository
import kotlinx.coroutines.launch

class GalleryViewModel (
   context: Context
): ViewModel() {
   private val repository = Repository(context)
   var shiftList = ArrayList<ShiftList>()
   var year = 9999
   var month = 99

   val isLog : LiveData<Int>
      get() = _isLog
   private val _isLog = MutableLiveData(99)


   private val _shiftList = MutableLiveData<ToMonthShitList>()

   fun getToMonthShift(){
      viewModelScope.launch {
         _isLog.postValue(1)
         _shiftList.value = repository.getToMonthShiftList(
            year = year,
            month = month
         )

         _shiftList.value!!.days.forEach {
            shiftList.add(ShiftList(
               year = year.toString(),
               month = month.toString(),
               day = it.toString()
            ))
         }
         Log.d("getShift",shiftList.toString())
         _isLog.postValue(0)
      }
   }
}