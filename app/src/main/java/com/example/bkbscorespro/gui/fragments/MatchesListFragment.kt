package com.example.bkbscorespro.gui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bkbscorespro.R
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*

class MatchesListFragment : Fragment() {

    private var listener: MatchesFragmetListener? = null

    companion object{
        fun newInstance () : MatchesListFragment{
            val newFragment = MatchesListFragment()

            return newFragment
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val view = inflater.inflate(R.layout.fragment_list_matches, container,false)

        bind(view)

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
        fun matchOnclick(int: Int)
    }


}
