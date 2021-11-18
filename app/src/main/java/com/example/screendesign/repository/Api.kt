package com.example.screendesign.repository

import com.example.screendesign.data.AccessToken
import com.example.screendesign.data.SeverBody
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {
    @POST("api/v1/auth/public")
    suspend fun getTopPageString(
    ): SeverBody

    //ログイン
    @POST("api/v1/auth/login")
    suspend fun login(
        @Query("employeeId") empId: String,
        @Query("password") password: String
    ) :AccessToken

    //ログアウト
    @POST("api/v1/auth/logout")
    suspend fun logout(
        @Query("access_token") token: String
    )

    //アクセストークンチェック
    @POST("api/v1/auth/check_access_token")
    suspend fun checkAccessToken(
        @Query("access_token") token:String
    ) :AccessToken
}