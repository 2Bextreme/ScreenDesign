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
    }

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.16.54.42")
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
        return@withContext sharedPref.getString(KEY_ACCESS_TOKEN, null) ?: return@withContext null.toString()
    }
    suspend fun accessTokenCheck(token:String):AccessToken = withContext(Dispatchers.Default){
        return@withContext api.checkAccessToken(token = token)
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
            newPass = newPass,
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