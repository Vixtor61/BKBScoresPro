package com.example.bkbscorespro.viewModels

import android.app.Application
import androidx.lifecycle.*
import com.example.bkbscorespro.database.BkbDatabase
import com.example.bkbscorespro.database.entities.Match
import com.example.bkbscorespro.database.entities.Team
import com.example.bkbscorespro.database.repositories.TeamRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TeamViewModel(application: Application) : AndroidViewModel(application){
    lateinit var currentTeamA: Team
    lateinit var currentTeamB: Team
    var idA:Long= 1
    var idB:Long= 1

    lateinit var allTeam: LiveData<List<Team>>
    var currentScoreA: MutableLiveData<Int> = MutableLiveData<Int>(0)
    var currentScoreB: MutableLiveData<Int> = MutableLiveData<Int>(0)


    private var teamRepo:TeamRepo
    init {
        val teamDao = BkbDatabase.getInstance(application,viewModelScope).teamDao()
        teamRepo = TeamRepo(teamDao)
        allTeam = teamRepo.allTeams
    }

    fun insertA(team: Team){
         viewModelScope.launch (Dispatchers.IO) {
            idA=teamRepo.insert(team)
        }
    }
    fun insertB(team: Team){
        viewModelScope.launch (Dispatchers.IO) {
            idB=teamRepo.insert(team)
        }
    }
/*
    fun inserts(team: Team) :Long{
        return teamRepo.insert(team)

    }
*/
}