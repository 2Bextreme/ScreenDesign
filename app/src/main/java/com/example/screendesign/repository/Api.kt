package com.example.screendesign.repository

import com.example.screendesign.data.AccessToken
import com.example.screendesign.data.SeverBody
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {
    @POST("public")
    suspend fun getTopPageString(
    ): SeverBody

    //ログイン
    @POST("login")
    suspend fun login(
        @Query("employeeId") empId: String,
        @Query("password") password: String
    ) :AccessToken
}