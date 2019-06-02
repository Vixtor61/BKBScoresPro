package com.example.bkbscorespro.viewModels

import android.app.Application
import androidx.lifecycle.*
import com.example.bkbscorespro.database.BkbDatabase
import com.example.bkbscorespro.database.entities.Team
import com.example.bkbscorespro.database.repositories.TeamRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TeamViewModel(application: Application) : AndroidViewModel(application){
    lateinit var currentTeamA: Team
    lateinit var currentTeamB: Team
    var currentScoreA: MutableLiveData<Int> = MutableLiveData<Int>(0)
    var currentScoreB: MutableLiveData<Int> = MutableLiveData<Int>(0)


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