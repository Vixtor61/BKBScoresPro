package com.example.bkbscorespro.database.repositories

import androidx.annotation.WorkerThread
import com.example.bkbscorespro.database.daos.TeamDao
import com.example.bkbscorespro.database.entities.Team
import kotlinx.coroutines.Job

class TeamRepo(private val teamDao: TeamDao){
    var allTeams = teamDao.getAll()

    @WorkerThread
     suspend fun insert(team: Team):Long{
     return   teamDao.insert(team)
    }



}