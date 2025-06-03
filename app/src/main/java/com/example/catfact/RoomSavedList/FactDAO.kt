package com.example.catfact.RoomSavedList

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FactDAO {

    @Insert
   suspend fun insert(fact:RoomFactEntity): Long

   @Delete
   suspend fun delete(fact: RoomFactEntity): Int

   @Query("SELECT * FROM FactEntity")
   fun getAll():  LiveData<List<RoomFactEntity>>

}