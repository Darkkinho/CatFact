package com.example.catfact.RoomSavedList

import android.content.Context
import androidx.lifecycle.LiveData

class FactRepository(context:Context) {

    private val repository = FactDataBase.factgetInstace(context).factDao()

    suspend fun insert(factTranslate: String): Long {
        val factEntity = RoomFactEntity(factTranslate = factTranslate)
        return repository.insert(factEntity)
    }

    suspend fun delete(fact: RoomFactEntity): Int{
        return repository.delete(fact)
    }

    fun getAll(): LiveData<List<RoomFactEntity>>{
        return repository.getAll()
    }

}