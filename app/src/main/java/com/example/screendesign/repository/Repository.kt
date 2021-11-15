package com.example.screendesign.repository

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class Repository{
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.16.100.31")
        .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
        .build()
    private val api = retrofit.create(Api::class.java)

    suspend fun checkServer(
    ) = withContext(Dispatchers.IO) {
        api.getTopPageString(
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
}