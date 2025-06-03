package com.example.catfact.APICatList

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private var INSTANCE: Retrofit? = null
    private const val BASE_URL = "https://catfact.ninja/"

    private fun getRetrofitInstance(): Retrofit{
        val http = OkHttpClient.Builder()


        if (INSTANCE == null){
            INSTANCE =
                Retrofit.Builder().baseUrl(BASE_URL).
                client(http.build()).addConverterFactory(GsonConverterFactory.create()).build()
        }

        return INSTANCE!!
    }

    fun <S>createRetrofitService(service: Class<S>): S{
        return getRetrofitInstance().create(service)
    }

}