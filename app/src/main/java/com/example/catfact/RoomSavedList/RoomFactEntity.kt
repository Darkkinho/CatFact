package com.example.catfact.RoomSavedList

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FactEntity")
data class RoomFactEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "factTranslate")
    var factTranslate: String = "",

    @ColumnInfo(name = "length")
    var length: String= ""
)