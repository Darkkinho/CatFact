package com.example.catfact.RecycleViewFact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catfact.RoomSavedList.RoomFactEntity
import com.example.catfact.databinding.ListRecycleItemBinding

class FactAdapter : RecyclerView.Adapter<FactHolder>() {

    private lateinit var clickList: ClickList
    private lateinit var clickLong: ClickDelete
    private var touchEnabled = true
    private var listFact: List<RoomFactEntity> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactHolder {
       val item = ListRecycleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FactHolder(item, clickList, clickLong)
    }

    fun touchEnabled(enabled:Boolean){
        touchEnabled = enabled
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listFact.size
    }

    override fun onBindViewHolder(holder: FactHolder, position: Int) {
            holder.setTouchClick(touchEnabled)
            val listFactPosition = listFact[position]
            holder.bind(listFactPosition)

    }
    
    fun getAll(list: List<RoomFactEntity>){
        listFact = list
        notifyDataSetChanged()
    }

    fun attachedListener(clickListener: ClickList, clickDelete: ClickDelete){
        clickList = clickListener
        clickLong = clickDelete
    }

}