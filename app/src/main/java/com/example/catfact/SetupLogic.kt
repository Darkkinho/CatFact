package com.example.catfact

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catfact.Fragment.HistoricFragment.HistoricListViewModel
import com.example.catfact.Fragment.HomeCatFragment.HomeCatViewModel
import com.example.catfact.RecycleViewFact.ClickDelete
import com.example.catfact.RecycleViewFact.ClickList
import com.example.catfact.RecycleViewFact.FactAdapter
import com.example.catfact.RoomSavedList.RoomFactEntity

class SetupLogic {

    private lateinit var viewModelCat : HomeCatViewModel
    private lateinit var historicModel: HistoricListViewModel
    private var adapter = FactAdapter()
    private var id = 0


    fun logicHistoric(owner: ViewModelStoreOwner ,context: Context , lifecycle: LifecycleOwner,
    recyclerView: RecyclerView?, imageButtonRemove:ImageButton?, linearTextView:LinearLayout?,
    textViewId: TextView?){

        historicModel = ViewModelProvider(owner)[HistoricListViewModel::class.java]

        recyclerView?.layoutManager = LinearLayoutManager(context.applicationContext)
        recyclerView?.adapter = adapter
        linearTextView?.alpha = 0f

        val click = object : ClickList {
            override fun onClick(fact: RoomFactEntity) {
                clickChangePopUp(fact, linearTextView, textViewId, imageButtonRemove, recyclerView, context)
            }
        }

        val clickDelete = object : ClickDelete {
            override fun onCLick(fact: RoomFactEntity) {

                id = fact.id

                if (id >= 0) {
                    historicModel.deleteFact(id)
                    id = 0
                }
            }

        }

        imageButtonRemove?.setOnClickListener {
            removeChangePopUp(linearTextView, textViewId, imageButtonRemove, recyclerView, context)
        }

        adapter.attachedListener(click, clickDelete)


        historicModel.listFact.observe(lifecycle){ listFact ->

            adapter.getAll(listFact)

        }

        historicModel.factDelete.observe(lifecycle){
            historicModel.listFact
        }

    }

   private fun clickChangePopUp(fact: RoomFactEntity, linearLayoutText: LinearLayout?, textViewId:TextView?, imageButtonRemove: ImageButton?, recyclerView: RecyclerView?, context: Context){
       linearLayoutText?.visibility = View.VISIBLE
        textViewId?.text = fact.factTranslate
        imageButtonRemove?.visibility = View.VISIBLE
        adapter.touchEnabled(false)
        enabledRecycle(false, recyclerView, context)
        recyclerView?.alpha = 0.5f
        recyclerView?.overScrollMode = View.OVER_SCROLL_NEVER

       if (linearLayoutText != null) {
           linearLayoutText.animate().alpha(1f).setDuration(1000).start()
       }

    }

    private fun removeChangePopUp(linearLayoutText: LinearLayout?, textViewId:TextView?, imageButtonRemove: ImageButton?, recyclerView: RecyclerView?,context: Context){
        if (linearLayoutText != null) {
            linearLayoutText.animate().alpha(0f).setDuration(0).start()
        }

        linearLayoutText?.visibility = View.GONE

        textViewId?.text = ""
        imageButtonRemove?.visibility = View.GONE
        adapter.touchEnabled(true)
        enabledRecycle(true, recyclerView, context)
        recyclerView?.alpha = 1f
        recyclerView?.overScrollMode = View.OVER_SCROLL_ALWAYS
    }

    private fun enabledRecycle(enable:Boolean, recyclerView: RecyclerView?, context: Context){
        if (recyclerView != null){
        recyclerView.isPressed = enable
        recyclerView.isEnabled = enable
        recyclerView.isNestedScrollingEnabled = enable
        recyclerView.isClickable = enable
        recyclerView.isActivated = enable

        recyclerView.layoutManager = object : LinearLayoutManager(context){

            override fun canScrollVertically(): Boolean {
                return enable
            }

        }
        }
    }

    fun logicCat(context:ViewModelStoreOwner, lifecycle: LifecycleOwner ,progressId: ProgressBar?, textFact: TextView?, saveButton:Button?, buttonAdd:Button?){

        viewModelCat = ViewModelProvider(context)[HomeCatViewModel::class.java]

        buttonAdd?.setOnClickListener {

            progressId?.visibility = View.VISIBLE
            textFact?.visibility = View.GONE
            saveButton?.visibility = View.GONE
            viewModelCat.fact()
        }

        viewModelCat.postFact.observe(lifecycle){

            textFact?.text = it.fact
            textFact?.visibility = View.VISIBLE
            progressId?.visibility = View.GONE
            saveButton?.visibility = View.VISIBLE
        }

        saveButton?.setOnClickListener {

            viewModelCat.insertFact()

        }

    }

}