package com.example.screendesign.repository

import com.example.screendesign.data.AccessToken
import com.example.screendesign.data.Result
import com.example.screendesign.data.ToMonthShitList
import retrofit2.http.*

interface Api {
    //提出した自身のシフトを取得
    @GET("api/shift_hope/shift_hope")
    suspend fun getSubmittedShift(
        @Header("Authorization") token: String,
        @Query("year") year: Int,
        @Query("month") month: Int
    ): ToMonthShitList

    //当月のシフト取得
    @GET("api/shift/shift_check")
    suspend fun shiftCheck(
        @Header("Authorization") token: String,
        @Query("year") year: Int,
        @Query("month") month: Int
    ): ToMonthShitList

    //パスワード変更
    @POST("api/auth_info/password")
    suspend fun changePassword(
        @Header("Authorization") token: String,
        @Query("newPassword1") newPass1: String,
        @Query("newPassword2") newPass2: String,
        @Query("oldPassword") oldPass: String
    ): Result

    //パスワードを忘れた時用
    @POST("api/auth_info/forgot_password")
    suspend fun forgetPassword(
        @Query("employeeId") empId: String,
        @Query("mailAddress") mailAddress: String
    ): Result

    //メールアドレス変更
    @POST("api/employee/change_mailAddress")
    suspend fun changeMailAddress(
        @Header("Authorization") token: String,
        @Query("mailAddress") mailAddress: String
    )

    //シフト提出
    @POST("api/shift_hope/shift_hope")
    suspend fun shiftHandover(
        @Header("Authorization") token: String,
        @Query("year") year: Int,
        @Query("month") month: Int,
        @Query("days") days: List<Int>
    ): Result


    //提出済みシフト削除
    @DELETE("api/shift_hope/shift_hope")
    suspend fun deleteShift(
        @Header("Authorization") token: String
    )


    //ログイン
    @POST("api/auth/login")
    suspend fun login(
        @Query("employeeId") empId: String,
        @Query("password") password: String
    ): AccessToken

    //ログアウト
    @POST("api/auth/logout")
    suspend fun logout(
        @Header("Authorization") token: String
    )

    //アクセストークンチェック
    @GET("api/auth/check_access_token")
    suspend fun checkAccessToken(
        @Header("Authorization") token: String
    ): AccessToken
}