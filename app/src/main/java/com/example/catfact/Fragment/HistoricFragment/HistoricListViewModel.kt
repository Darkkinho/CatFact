package com.example.catfact.Fragment.HistoricFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.catfact.RoomSavedList.FactRepository
import com.example.catfact.RoomSavedList.RoomFactEntity
import kotlinx.coroutines.launch

class HistoricListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository =  FactRepository(application)

    val listFact: LiveData<List<RoomFactEntity>> = repository.getAll()

    private val _factDelete = MutableLiveData<Long>()
    val factDelete: LiveData<Long> = _factDelete

    fun deleteFact(id:Int){
        viewModelScope.launch {

            val model = RoomFactEntity().apply {
                this.id = id
            }

            _factDelete.value = repository.delete(model).toLong()

        }
    }
}