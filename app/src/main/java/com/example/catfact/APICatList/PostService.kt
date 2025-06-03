package com.example.catfact.APICatList

import retrofit2.http.GET

interface PostService {
    @GET("fact")
    suspend fun getFact(): CatEntity
}