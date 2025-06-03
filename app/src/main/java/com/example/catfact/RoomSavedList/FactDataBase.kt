package com.example.catfact.RoomSavedList

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities =  [RoomFactEntity::class], version = 1, exportSchema = false)
abstract class FactDataBase: RoomDatabase() {

    abstract fun factDao (): FactDAO

    companion object {

        private var INSTANCE: FactDataBase? = null

        fun factgetInstace(context:Context): FactDataBase {

              return INSTANCE ?: synchronized(FactDataBase::class.java){

                val instance = Room.databaseBuilder(context, FactDataBase::class.java, "fact_database").build()

                INSTANCE = instance
                instance
            }

        }

    }

}