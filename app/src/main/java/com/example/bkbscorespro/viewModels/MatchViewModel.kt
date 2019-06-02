package com.example.bkbscorespro.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bkbscorespro.database.BkbDatabase
import com.example.bkbscorespro.database.entities.Match
import com.example.bkbscorespro.database.entities.Team
import com.example.bkbscorespro.database.repositories.MatchRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MatchViewModel(application: Application) : AndroidViewModel(application){
    lateinit var allMatches: LiveData<List<Match>>


    private var matchRepo: MatchRepo
    init {
        val matchDao = BkbDatabase.getInstance(application,viewModelScope).matchDao()
        matchRepo = MatchRepo(matchDao)
        allMatches = matchRepo.allMatchs
    }

    fun insert(match: Match){
        viewModelScope.launch (Dispatchers.IO) {
            matchRepo.insert(match)
        }
    }

}