package com.example.bkbscorespro.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.*
import com.example.bkbscorespro.R
import com.example.bkbscorespro.database.entities.Match
import com.example.bkbscorespro.database.entities.Team
import com.example.bkbscorespro.viewModels.MatchViewModel
import kotlinx.android.synthetic.main.activity_show_match.*

class ShowMatchActivity : AppCompatActivity(),LifecycleOwner {
    lateinit var matchViewModel: MatchViewModel
    private lateinit var lifecycleRegistry: LifecycleRegistry

    lateinit var match: Match
    lateinit var teamA: Team
    lateinit var teamB: Team
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_match)
        lifecycleRegistry= LifecycleRegistry(this)
        lifecycleRegistry.markState(Lifecycle.State.CREATED)
       val extras = intent.extras
        match = extras.getSerializable("EXTRA") as Match
        matchViewModel = ViewModelProviders.of(this).get(MatchViewModel::class.java)
        matchViewModel.getTeamById(match.teamA).observe(this, Observer { team->
            team?.let {teamA=team
                    tv_name_a_ac.text = team.teamName
            }
        })
        matchViewModel.getTeamById(match.teamB).observe(this, Observer { team->
            team?.let {teamB=team
                tv_name_b_ac.text = team.teamName
            }
        })

        bind()

    }


private fun bind(){
    tv_score_a_ac.text = match.scoreTeamA.toString()
    tv_score_b_ac.text = match.scoreTeamB.toString()
    date.text = match.date
}


    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }
}
