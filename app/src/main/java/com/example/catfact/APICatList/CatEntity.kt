package com.example.catfact.APICatList

import com.google.gson.annotations.SerializedName

data class CatEntity(

    @SerializedName("fact")
    var fact:String? = null,

    @SerializedName("length")
    var length:Int

)
