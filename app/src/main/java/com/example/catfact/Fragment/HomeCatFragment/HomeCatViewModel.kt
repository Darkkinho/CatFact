package com.example.catfact.Fragment.HomeCatFragment

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.application
import androidx.lifecycle.viewModelScope
import com.example.catfact.APICatList.CatEntity
import com.example.catfact.APICatList.PostService
import com.example.catfact.APICatList.RetrofitClient
import com.example.catfact.APITranslate.RetrofitTranslateClient
import com.example.catfact.R
import com.example.catfact.RoomSavedList.FactRepository
import com.example.catfact.RoomSavedList.RoomFactEntity
import kotlinx.coroutines.launch
import java.util.Locale

class HomeCatViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = FactRepository(application.applicationContext)

    private val _postFact = MutableLiveData<CatEntity>()
    val postFact:LiveData<CatEntity> = _postFact

    private val _fact = MutableLiveData<Long>()
    val fact: LiveData<Long> get() = _fact

   private val service = RetrofitClient.createRetrofitService(PostService::class.java)

    private var translatedtxt: CatEntity = CatEntity("",0)

    fun fact(){

        val retrofitTranslate = RetrofitTranslateClient

        viewModelScope.launch {

            try {
                  val fact = service.getFact()
                  val translate = Locale.getDefault().language

                retrofitTranslate.translateText(fact.fact.toString(), "en", translate) { result ->

                    if (result != null) {
                         translatedtxt = fact.copy(fact = result)
                        _postFact.value = translatedtxt

                    } else {
                        Toast.makeText(application.applicationContext,
                            application.applicationContext.getString(R.string.translation_error), Toast.LENGTH_SHORT).show()
                    }

                }

            }catch (e:Exception){
                Toast.makeText(application.applicationContext,
                    application.applicationContext.getString(R.string.error), Toast.LENGTH_SHORT).show()
            }

        }

    }
        fun insertFact() {
            viewModelScope.launch {
                val model = RoomFactEntity().apply {
                    this.factTranslate = translatedtxt.fact ?: application.applicationContext.getString(R.string.empty_field)
                    this.length = translatedtxt.length.toString()
                }
                _fact.value = repository.insert(model.factTranslate)

            }
        }
}