package com.example.bkbscorespro.gui.fragments

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bkbscorespro.R
import com.example.bkbscorespro.database.entities.Match
import com.example.bkbscorespro.gui.adapters.MatchesAdapter
import com.example.bkbscorespro.viewModels.MatchViewModel
import com.example.bkbscorespro.viewModels.TeamViewModel
import kotlinx.android.synthetic.main.fragment_list_matches.view.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*

class MatchesListFragment : Fragment() {

    private var listener: MatchesFragmetListener? = null
    lateinit var teamViewModel: TeamViewModel
    lateinit var matchViewModel: MatchViewModel
    private lateinit var matchesAdapter : MatchesAdapter

    companion object{
        fun newInstance () : MatchesListFragment{
            val newFragment = MatchesListFragment()

            return newFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        matchViewModel = ViewModelProviders.of(this).get(MatchViewModel::class.java)
        teamViewModel = ViewModelProviders.of(this).get(TeamViewModel::class.java)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val view = inflater.inflate(R.layout.fragment_list_matches, container,false)

        bind(view)
        initRecyclerView(view)
        return view
    }

    /*
        // TODO: Rename method, update argument and hook method into UI event
        fun onButtonPressed() {
            listener?.newMatch()
        }
      */
    fun bind(view: View){

    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MatchesFragmetListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface MatchesFragmetListener {
        // TODO: Update argument type and name
        fun matchOnclick(match: Match)
    }




    fun initRecyclerView( container: View){

        val linearLayoutManager = LinearLayoutManager(this.context)
           // Log.i("ERROR_RV",teamViewModel.allTeam.value?.size.toString())


            matchesAdapter = MatchesAdapter({ match:Match-> listener?.matchOnclick(match)})
            container.rv_match_list.adapter = matchesAdapter

            container.rv_match_list.apply {
                setHasFixedSize(true)
                layoutManager = linearLayoutManager
            }

            matchViewModel.allMatches.observe(this, Observer { matches->
                matches.let {
                    matchesAdapter.changeDataSet(matches)
                }
            })
            teamViewModel.allTeam.observe(this, Observer { teams->
                teams.let {
                    matchesAdapter.changeTeamSet(teams)
                }
            })



    }


}
