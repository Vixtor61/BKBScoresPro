package com.example.bkbscorespro.database.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.bkbscorespro.database.daos.MatchDao
import com.example.bkbscorespro.database.entities.Match
import com.example.bkbscorespro.database.entities.Team

class MatchRepo(private val matchDao: MatchDao){
    var allMatchs = matchDao.getAll()

    @WorkerThread
    suspend fun insert(match: Match){
        matchDao.insert(match)
    }
    @WorkerThread
     fun getTeamById(teamId: Int): LiveData<Team> {
        return   matchDao.getTeamById(teamId)
    }
}