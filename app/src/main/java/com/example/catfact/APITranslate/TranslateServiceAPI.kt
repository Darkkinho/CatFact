package com.example.catfact.APITranslate

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TranslateServiceAPI {
    @GET("translate")
    fun translate(
        @Query("sl") sourceLang: String,
        @Query("dl") destLang: String,
        @Query("text") text: String

    ): Call<TranslateResponse>
}