package com.example.bkbscorespro.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bkbscorespro.R
import com.example.bkbscorespro.gui.fragments.MainFragment
import com.example.bkbscorespro.gui.fragments.MatchesListFragment
import com.example.bkbscorespro.gui.fragments.NewMatchFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*

class MainActivity : AppCompatActivity() ,MainFragment.OnFragmentInteractionListener,NewMatchFragment.NewMatchListener,MatchesListFragment.MatchesFragmetListener{
    override fun matchOnclick(int: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun startMatch(teamA:String,teamB:String) {
        Toast.makeText(this,teamA,Toast.LENGTH_LONG).show()
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

        mainFragment = MainFragment.newInstance()
        changeFragment(R.id.og_fragment,mainFragment)


    }
    private fun changeFragment(id:Int,fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(id,fragment).commit()
    }
}
