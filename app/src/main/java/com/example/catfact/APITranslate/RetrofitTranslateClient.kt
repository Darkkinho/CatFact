package com.example.catfact.APITranslate

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitTranslateClient {

    private const val BASE_URL = "https://ftapi.pythonanywhere.com/"

        private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
            GsonConverterFactory.create()).build()

        private val translationApi = retrofit.create(TranslateServiceAPI::class.java)

        fun translateText(text: String, sourceLang:String, destLang:String, onResult:(String?) -> Unit) {

            val call = translationApi.translate(sourceLang, destLang, text)

            call.enqueue(object: Callback<TranslateResponse> {
                override fun onResponse(call: Call<TranslateResponse>, response: Response<TranslateResponse>) {

                    if (response.isSuccessful){
                        val responseBody = response.body()
                        val translateText = responseBody?.destinationText

                        if (translateText != null){
                             onResult(translateText)
                        } else{
                            onResult(null)
                        }
                    } else{
                        onResult(null)
                    }

                }

                override fun onFailure(call: Call<TranslateResponse>, t: Throwable) {
                    onResult(null)
                }


            }

            )

        }


}