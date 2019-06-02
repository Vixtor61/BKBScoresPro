package com.example.bkbscorespro.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bkbscorespro.database.entities.Match

@Dao
interface MatchDao{

    @Query("SELECT * FROM bkb_match")
    fun getAll(): LiveData<List<Match>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(match: Match)

    @Query("DELETE FROM bkb_match")
    fun nukeTable()

}