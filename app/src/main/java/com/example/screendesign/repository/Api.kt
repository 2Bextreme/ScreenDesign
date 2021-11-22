package com.example.screendesign.repository

import com.example.screendesign.data.AccessToken
import com.example.screendesign.data.Result
import com.example.screendesign.data.SeverBody
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {
    @POST("api/v1/auth/public")
    suspend fun getTopPageString(
    ): SeverBody

    //シフト提出
    @POST("api/v1/shift/shift_request")
    suspend fun shiftHandover(
        @Header("Authorization") token:String,
        @Query("year") year:Int,
        @Query("month") month:Int,
        @Query("days") days:List<Int>
    ) :Result

    //ログイン
    @POST("api/v1/auth/login")
    suspend fun login(
        @Query("employeeId") empId: String,
        @Query("password") password: String
    ) :AccessToken

    //ログアウト
    @POST("api/v1/auth/logout")
    suspend fun logout(
        @Header("Authorization") token:String
    )

    //アクセストークンチェック
    @GET("api/v1/auth/check_access_token")
    suspend fun checkAccessToken(
        @Header("Authorization") token:String
    ) :AccessToken
}