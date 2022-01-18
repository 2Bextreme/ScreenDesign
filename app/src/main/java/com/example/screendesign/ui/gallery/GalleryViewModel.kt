package com.example.screendesign.ui.gallery

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.screendesign.data.ToMonthShitList
import com.example.screendesign.repository.Repository
import kotlinx.coroutines.launch

class GalleryViewModel (
   context: Context
): ViewModel() {
   private val repository = Repository(context)
   var year = 9999
   var month = 99

   val isLog : LiveData<Int>
      get() = _isLog
   private val _isLog = MutableLiveData(99)

   val shiftList : LiveData<ToMonthShitList>
      get() = _shiftList
   private val _shiftList = MutableLiveData<ToMonthShitList>()

   fun getToMonthShift(){
      viewModelScope.launch {
         _isLog.postValue(1)
         _shiftList.value = repository.getToMonthShiftList(
            year = year,
            month = month
         )
         Log.d("getShift",shiftList.toString())
         _isLog.postValue(0)
      }
   }
}