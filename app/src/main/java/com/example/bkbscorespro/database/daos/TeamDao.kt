package com.example.bkbscorespro.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bkbscorespro.database.entities.Team

@Dao
interface TeamDao{
    @Query("SELECT * FROM team")
    fun getAll(): LiveData<List<Team>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(team:Team):Long

    @Query("DELETE FROM team")
    fun nukeTable()
}