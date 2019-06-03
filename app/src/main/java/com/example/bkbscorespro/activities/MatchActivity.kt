package com.example.bkbscorespro.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.*
import com.example.bkbscorespro.R
import com.example.bkbscorespro.database.entities.Match
import com.example.bkbscorespro.database.entities.Team
import com.example.bkbscorespro.viewModels.MatchViewModel
import com.example.bkbscorespro.viewModels.TeamViewModel
import kotlinx.android.synthetic.main.activity_match.*




class MatchActivity : AppCompatActivity(),LifecycleOwner {
    private lateinit var lifecycleRegistry: LifecycleRegistry
    lateinit var teamViewModel: TeamViewModel
    lateinit var matchViewModel: MatchViewModel
    lateinit var teamA: String
    lateinit var teamB: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleRegistry= LifecycleRegistry(this)
        lifecycleRegistry.markState(Lifecycle.State.CREATED)
        setContentView(R.layout.activity_match)
        val extras = intent.extras
        teamA =extras.getString("TEAM_A")
        teamB = extras.getString("TEAM_B")
        matchViewModel = ViewModelProviders.of(this).get(MatchViewModel::class.java)
        teamViewModel = ViewModelProviders.of(this).get(TeamViewModel::class.java)
        teamViewModel.currentTeamA = Team(teamA)
        teamViewModel.currentTeamB = Team(teamB)
       teamViewModel.insertA(teamViewModel.currentTeamA)
        teamViewModel.insertB(teamViewModel.currentTeamB)
     //   Log.i("CURRENT",teamViewModel.idA.toString())
        teamViewModel.currentScoreA.observe(this, Observer { score->
                score?.let {
                    score_a.text = score.toString()
                }


        })

        teamViewModel.currentScoreB.observe(this, Observer { score->
            score?.let {
                score_b.text = score.toString()
            }

        })

        bind()
        }




    private fun bind(){
        tv_team_a.text = teamA
        tv_team_b.text = teamB


        btn_2_a.setOnClickListener { score(teamA,2) }
        btn_3_a.setOnClickListener { score(teamA,3) }


        btn_2_b.setOnClickListener { score(teamB,2) }
        btn_3_b.setOnClickListener { score(teamB,3) }
        end_match.setOnClickListener { saveMatch() }
    }

    private fun score(team:String,points:Int){
        Log.i("CURRENT",teamViewModel.idA.toString())
        if (team == teamViewModel.currentTeamA.teamName){
            teamViewModel.currentScoreA.value = teamViewModel.currentScoreA.value!! + points
        }else{
            teamViewModel.currentScoreB.value = teamViewModel.currentScoreB.value!! + points
        }


    }
    fun saveMatch(){
        var match = Match(teamViewModel.idA.toInt(),teamViewModel.idB.toInt(),teamViewModel.currentScoreA.value!!,teamViewModel.currentScoreB.value!!)

        Log.i("CURRENT_MATCH",match.scoreTeamA.toString() + "score " + match.scoreTeamB.toString()+ " teamA" + match.teamA.toString()+ "teamB" + match.teamB.toString())
        matchViewModel.insert(match)
        intent = Intent(this,MainActivity::class.java)
        startActivity(intent)

    }
    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }
}



