package com.onyango.habits.data

import androidx.room.*
import com.onyango.habits.model.Habit
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {

    @Insert (onConflict =  OnConflictStrategy.IGNORE)
    suspend fun insertHabit(habit: Habit)

    @Query("SELECT * FROM habit ORDER BY id ASC")
    fun getAllData(): Flow<List<Habit>>

}