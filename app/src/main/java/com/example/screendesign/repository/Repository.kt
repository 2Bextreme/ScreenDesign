package com.example.screendesign.repository

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import com.example.screendesign.data.AccessToken
import com.example.screendesign.data.Result


class Repository (
    context: Context
        ){
    companion object{
        private const val KEY_ACCESS_TOKEN = "access_token"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_NOTIFICATION = "notification"
    }

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://www.rin-ats.com")
        .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
        .build()
    private val api = retrofit.create(Api::class.java)

    val sharedPref:SharedPreferences = context.applicationContext.getSharedPreferences(
        "Users",Context.MODE_PRIVATE)

    //当月のシフト取得
    suspend fun getToMonthShiftList(
        year: Int,
        month: Int
    ) = withContext(Dispatchers.IO){
        api.shiftCheck(
            token = get(),
            year = year,
            month = month
        )
    }

    //提出した自身のシフトを取得
    suspend fun getSubmittedShift(
        year: Int,
        month: Int
    ) = withContext(Dispatchers.IO){
        api.getSubmittedShift(
            token = get(),
            year = year,
            month = month
        )
    }

    //accessTokenのセット・ゲット・チェック
    suspend fun set(
        token:String
    ) = withContext(Dispatchers.Main){
        sharedPref.edit {
            putString(KEY_ACCESS_TOKEN,token)
        }
    }
    suspend fun get(
    ): String = withContext(Dispatchers.Main) {
        return@withContext sharedPref.getString(KEY_ACCESS_TOKEN, null) ?: null.toString()
    }
    suspend fun accessTokenCheck(token:String):AccessToken = withContext(Dispatchers.Default){
        return@withContext api.checkAccessToken(token = token)
    }
    //

    //userIdのセット・ゲット
    suspend fun setUserId(
            userId:String
    ) = withContext(Dispatchers.Main){
        sharedPref.edit {
            putString(KEY_USER_ID,userId)
        }
    }
    suspend fun getUserId(
    ): String = withContext(Dispatchers.Main) {
        return@withContext sharedPref.getString(KEY_USER_ID, null) ?: null.toString()
    }
    //

    //通知の有無
    suspend fun setNotification(
        bool:String,
        userId: String
    ) = withContext(Dispatchers.Main){
        sharedPref.edit {
            putString(KEY_NOTIFICATION + userId,bool)
        }
    }
    suspend fun getNotification(
    ): String = withContext(Dispatchers.Main) {
        return@withContext sharedPref.getString(KEY_NOTIFICATION + getUserId(), null) ?: null.toString()
    }
    //

    //シフト提出・削除
    suspend fun shiftHandOver(
        year: Int,
        month: Int,
        days: List<Int>
    )= withContext(Dispatchers.IO){
        api.shiftHandover(
            token = get(),
            year = year,
            month = month,
            days = days
        )
    }

    suspend fun deleteShift()= withContext(Dispatchers.Main){
        api.deleteShift(
            token = get()
        )
    }
    //

    //パスワード変更(ログインしていないとき)
    suspend fun forgetPassword(
        empId :String,
        mailAddress:String
    ) = withContext(Dispatchers.IO){
        api.forgetPassword(
            empId = empId,
            mailAddress = mailAddress
        )
    }

    //メールアドレス変更
    suspend fun changeMailAddress(
        mail:String
    ) = withContext(Dispatchers.Main){
        api.changeMailAddress(
            token = get(),
            mailAddress = mail
        )
    }

    //パスワード変更
    suspend fun changePassword(
        newPass:String,
        oldPass:String
    ) = withContext(Dispatchers.Main){
        api.changePassword(
            token = get(),
            newPass1 = newPass,
            newPass2 = newPass,
            oldPass = oldPass
        )
    }

    suspend fun login(
        empId :String,
        pass :String
    ) = withContext(Dispatchers.IO) {
        api.login(
            empId = empId,
            password = pass
        )
    }

    suspend fun logout(token:String) = withContext(Dispatchers.IO){
        api.logout(
            token = token
        )
    }
}