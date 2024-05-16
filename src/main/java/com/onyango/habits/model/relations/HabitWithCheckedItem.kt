package com.onyango.habits.model.relations

import com.onyango.habits.model.CheckedItem

data class HabitWithCheckedItem (

//    @Embedded val habit: Habit,
//    @Relation(
//        parentColumn = "habitName",
//        entityColumn = "habitName"
//    )
    val checkedItems: List<CheckedItem>
)