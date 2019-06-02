package com.example.bkbscorespro.database.repositories

import androidx.annotation.WorkerThread
import com.example.bkbscorespro.database.daos.MatchDao
import com.example.bkbscorespro.database.entities.Match

class MatchRepo(private val matchDao: MatchDao){
    var allMatchs = matchDao.getAll()

    @WorkerThread
    suspend fun insert(match: Match){
        matchDao.insert(match)
    }


}