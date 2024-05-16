package com.onyango.habits.data

import com.onyango.habits.model.Habit

class HabitRepository(private val habitDao: HabitDao) {

    fun getAllData() = habitDao.getAllData()

    suspend fun insertHabit(habit: Habit) {
        habitDao.insertHabit(habit)
    }
}