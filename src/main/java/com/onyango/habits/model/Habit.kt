package com.onyango.habits.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Habit(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    /*val checked: Boolean,*/
    val habitName: String,
    /*val lastCheckedDate: String,
    val lastWeekCheckCount: Int,*/
    val targetWeekCheckCount: Int,
    /*val lastMonthCheckCount: Int*/
)
