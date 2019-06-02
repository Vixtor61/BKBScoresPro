package com.example.bkbscorespro.gui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bkbscorespro.R
import com.example.bkbscorespro.database.entities.Match
import com.example.bkbscorespro.database.entities.Team
import kotlinx.android.synthetic.main.rv_match.view.*

class MatchesAdapter ( val clickListener: (Match) -> Unit) : RecyclerView.Adapter<MatchesAdapter.MatchViewHolder>(){
    var matches: List<Match> = emptyList()
     var teams:List<Team> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_match, parent, false)

        return MatchViewHolder(view)
    }

    override fun getItemCount(): Int = matches.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) = holder.bind(matches[position],teams, clickListener)

     fun changeDataSet(newMatchSet: List<Match>) {
        this.matches = newMatchSet

        notifyDataSetChanged()
    }
    fun changeTeamSet(newTeamSet: List<Team>) {

        this.teams =newTeamSet
        notifyDataSetChanged()
    }

    class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(match: Match, teams: List<Team>, clickListener: (Match) -> Unit) = with(itemView) {

            tv_score_a.text = match.scoreTeamA.toString()
            tv_score_b.text = match.scoreTeamB.toString()
            teams.forEach { team->
                if (team.id==match.teamA){
                    tv_name_a.text = team.teamName
                }
                if (team.id==match.teamB){
                    tv_name_b.text = team.teamName

                }
            }

            itemView.setOnClickListener { clickListener(match) }
        }
    }
}