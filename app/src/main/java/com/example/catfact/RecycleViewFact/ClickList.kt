package com.example.catfact.RecycleViewFact

import com.example.catfact.RoomSavedList.RoomFactEntity

interface ClickList {
    fun onClick(fact:RoomFactEntity)
}

interface ClickDelete {
    fun onCLick(fact: RoomFactEntity)
}
