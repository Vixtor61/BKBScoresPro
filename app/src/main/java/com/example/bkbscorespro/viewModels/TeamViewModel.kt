package com.example.bkbscorespro.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bkbscorespro.database.BkbDatabase
import com.example.bkbscorespro.database.entities.Team
import com.example.bkbscorespro.database.repositories.TeamRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TeamViewModel(application: Application) : AndroidViewModel(application){
    lateinit var currentTeamA: Team
    lateinit var currentTeamB: Team


    private var teamRepo:TeamRepo
    init {
        val teamDao = BkbDatabase.getInstance(application,viewModelScope).teamDao()
        teamRepo = TeamRepo(teamDao)
    }

    fun insert(team: Team){
        viewModelScope.launch (Dispatchers.IO) {
            teamRepo.insert(team)
        }
    }

}