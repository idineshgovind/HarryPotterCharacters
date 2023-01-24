package com.example.harrypottercharacters.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET



private const val BASE_URL = "https://harry-potter-api-en.onrender.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface HpApiService{
    @GET("characters")
    fun getCharactersAsync():
            Deferred<List<HpProperty>>
}

object HpApi{
    val retrofitService : HpApiService by lazy {
        retrofit.create(HpApiService::class.java)
    }
}