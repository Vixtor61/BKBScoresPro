package com.example.bkbscorespro.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.bkbscorespro.R
import com.example.bkbscorespro.database.entities.Match
import com.example.bkbscorespro.database.entities.Team
import com.example.bkbscorespro.gui.fragments.MainFragment
import com.example.bkbscorespro.gui.fragments.MatchesListFragment
import com.example.bkbscorespro.gui.fragments.NewMatchFragment
import com.example.bkbscorespro.viewModels.TeamViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_show_match.*
import kotlinx.android.synthetic.main.fragment_main.*
import java.io.Serializable




class MainActivity : AppCompatActivity() ,MainFragment.OnFragmentInteractionListener,NewMatchFragment.NewMatchListener,MatchesListFragment.MatchesFragmetListener{
    override fun matchOnclick(match: Match) {
        var intent = Intent(this,ShowMatchActivity::class.java)
        intent.putExtra("EXTRA",match)
        startActivity(intent)
    }

    private lateinit var teamViewModel: TeamViewModel



    override fun startMatch(teamA:String,teamB:String) {




            if (teamA == teamB){
                Toast.makeText(this,"Team A and B must be different",Toast.LENGTH_SHORT).show()
            }
        else if (teamA == "" || teamB == ""){
                Toast.makeText(this,"You must enter two teams",Toast.LENGTH_SHORT).show()
            }
        else{
                intent = Intent(this,MatchActivity::class.java).apply {
                    putExtra("TEAM_A",teamA)
                    putExtra("TEAM_B",teamB)
                }
                startActivity(intent)

            }


    }

    private lateinit var mainFragment: MainFragment
    private lateinit var newMatchFragment: NewMatchFragment
    private lateinit var matchesListFragment: MatchesListFragment

    override fun changeFragment(id: Int) {
        if (id == ln_new.id){

            if (!this::newMatchFragment.isInitialized){
                Log.i("POINT_1","u here")
                newMatchFragment = NewMatchFragment.newInstance()
                changeFragment(R.id.og_fragment,newMatchFragment)

            }
            else{
                changeFragment(R.id.og_fragment,newMatchFragment)
            }

        }
        if(id == ln_saved.id){
            if (!this::matchesListFragment.isInitialized){
                Log.i("POINT_2","u here")
                matchesListFragment = MatchesListFragment.newInstance()
                changeFragment(R.id.og_fragment,matchesListFragment)

            }
            else{
                changeFragment(R.id.og_fragment,matchesListFragment)
            }
        }


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        teamViewModel = ViewModelProviders.of(this).get(TeamViewModel::class.java)
        mainFragment = MainFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.og_fragment,mainFragment).commit()



    }
    private fun changeFragment(id:Int,fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(id,fragment).addToBackStack(null).commit()
    }

    override fun onBackPressed() {
        Log.i("FRAGMENT_COUNT",supportFragmentManager.backStackEntryCount.toString())
        if(supportFragmentManager.backStackEntryCount > 1){
            supportFragmentManager.popBackStackImmediate()
        }
        else{
                super.onBackPressed()
        }
    }

}
