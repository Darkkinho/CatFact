package com.example.catfact.RecycleViewFact

import androidx.recyclerview.widget.RecyclerView
import com.example.catfact.RoomSavedList.RoomFactEntity
import com.example.catfact.databinding.ListRecycleItemBinding

class FactHolder (private val bind: ListRecycleItemBinding, private val click: ClickList, private val clickDelete: ClickDelete): RecyclerView.ViewHolder(bind.root) {

    private var touchEnabled = true

    fun setTouchClick(enabled:Boolean){
        touchEnabled = enabled
    }

    fun bind(fact: RoomFactEntity){

        bind.TextListFactId.text = fact.factTranslate

        bind.TextListFactId.setOnClickListener {
          if (touchEnabled) {
              click.onClick(fact)
          }
        }

        bind.ButtonDeleteItemId.setOnClickListener {

          if (touchEnabled) {

            clickDelete.onCLick(fact)

            }
        }


    }

}