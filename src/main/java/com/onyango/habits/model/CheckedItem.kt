package com.onyango.habits.model

//@Entity
data class CheckedItem(

    //@PrimaryKey(autoGenerate = false)
    val id: Int,
    val habitName: String,
    val date: String
)
