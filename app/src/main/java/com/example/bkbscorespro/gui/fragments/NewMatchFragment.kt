package com.example.bkbscorespro.gui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bkbscorespro.R
import kotlinx.android.synthetic.main.fragment_new_match.*
import kotlinx.android.synthetic.main.fragment_new_match.view.*

class NewMatchFragment() : Fragment(){
    private var listener: NewMatchListener? = null



    companion object{
        fun newInstance () : NewMatchFragment{

            return NewMatchFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val view = inflater.inflate(R.layout.fragment_new_match, container,false)

        bind(view)

        return view
    }

    interface NewMatchListener{
        fun startMatch(teamA:String,teamB:String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NewMatchListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    fun bind(view: View){

      view.launch_match.setOnClickListener { listener?.startMatch(team_a.text.toString(),team_b.text.toString()) }
    }
}