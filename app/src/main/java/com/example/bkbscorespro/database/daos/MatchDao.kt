package com.example.bkbscorespro.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bkbscorespro.database.entities.Match
import com.example.bkbscorespro.database.entities.Team

@Dao
interface MatchDao{

    @Query("SELECT * FROM bkb_match")
    fun getAll(): LiveData<List<Match>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(match: Match)

    @Query("DELETE FROM bkb_match")
    fun nukeTable()

    @Query("SELECT * FROM team t WHERE :teamId = t.id")
    fun getTeamById(teamId: Int):LiveData<Team>

}