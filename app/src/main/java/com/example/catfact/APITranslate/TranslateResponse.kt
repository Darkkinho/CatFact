package com.example.catfact.APITranslate

import com.google.gson.annotations.SerializedName

data class TranslateResponse(
    @SerializedName("source-language")
    val sourceLanguage: String?,
    @SerializedName("source-text")
    val sourceText: String?,
    @SerializedName("destination-language")
    val destinationLanguage: String?,
    @SerializedName("destination-text")
    val destinationText: String?,
)

