package com.example.screendesign.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.screendesign.activity.TopPageActivity
import com.example.screendesign.repository.NotificationRepository
import com.example.screendesign.repository.NotificationWorker
import com.example.screendesign.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.TimeUnit

class HomeViewModel(
    @field:SuppressLint("StaticFieldLeak") private val context: Context
) : ViewModel() {
    private val repository = NotificationRepository(context)

    private val uploadWorkRequest: WorkRequest =
        PeriodicWorkRequest.Builder(NotificationWorker::class.java,15, TimeUnit.MINUTES).build()

    fun notificationEnd(){
        viewModelScope.launch {
            if (repository.getWorkId() != "null"){
                //通知を起動
                Log.d("通知","通知は起動しています。")
                Log.d("workId",repository.getWorkId())
                WorkManager
                    .getInstance(context)
                    .cancelWorkById(UUID.fromString(repository.getWorkId()))
                Log.d("通知","通知をキャンセルしました。")
                repository.setWorkId("null")
                Log.d("workId",repository.getWorkId())
            }
        }
    }

    fun notificationStart(){
        CoroutineScope(Dispatchers.Main).launch {
            if (repository.getWorkId() == "null"){
                //通知を起動
                Log.d("通知","通知は起動していません。")
                WorkManager
                    .getInstance(context)
                    .enqueue(uploadWorkRequest)
                repository.setWorkId(uploadWorkRequest.id.toString())
                Log.d("通知","通知を起動し,WorkIDを登録しました")
                Log.d("workId",repository.getWorkId())
            }
        }
    }

    fun checkNotification(){
        CoroutineScope(Dispatchers.Main).launch {
            Log.d("Fragment","HomeFragment")
            Log.d("workId",repository.getWorkId())
            if (repository.getWorkId() == "null"){
                Log.d("通知","通知は起動していません。")
            }else{
                Log.d("通知","通知は起動しています。")
            }
        }
    }
}