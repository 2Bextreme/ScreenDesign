package com.example.screendesign.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NotificationRepository (
    context: Context
        ){
    companion object{
        private const val KEY_WORK_ID = "work_id"
        private const val KEY_NOTIFIY_OBSERVER_SWITCH = "notify_observer_switch"
    }

    val sharedPref: SharedPreferences = context.applicationContext.getSharedPreferences(
        "Users",Context.MODE_PRIVATE)

    //WorkRequestIDのセット・ゲット
    suspend fun setWorkId(
        workId:String
    ) = withContext(Dispatchers.Main){
        sharedPref.edit {
            putString(KEY_WORK_ID,workId)
        }
    }
    suspend fun getWorkId(
    ): String = withContext(Dispatchers.Main) {
        return@withContext sharedPref.getString(KEY_WORK_ID, null) ?: return@withContext null.toString()
    }
    //

    //WorkRequestIDのセット・ゲット
    suspend fun setObserverBoolean(
        ovserver:Boolean
    ) = withContext(Dispatchers.Main){
        sharedPref.edit {
            putString(KEY_NOTIFIY_OBSERVER_SWITCH,ovserver.toString())
        }
    }
    suspend fun getObserverBoolean(
    ): String = withContext(Dispatchers.Main) {
        return@withContext sharedPref.getString(KEY_NOTIFIY_OBSERVER_SWITCH, null) ?: return@withContext null.toString()
    }
    //
}