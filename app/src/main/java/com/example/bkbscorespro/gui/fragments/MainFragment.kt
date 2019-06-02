package com.example.bkbscorespro.gui.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.bkbscorespro.R
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
/*
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
*/
/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MainFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class MainFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val view = inflater.inflate(R.layout.fragment_main, container,false)

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
        view.ln_new.setOnClickListener { listener?.changeFragment(ln_new.id) }
        view.ln_saved.setOnClickListener { listener?.changeFragment(ln_saved.id) }
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun changeFragment(id: Int)
    }

    companion object{
        fun newInstance () : MainFragment{
            val newFragment = MainFragment()

            return newFragment
        }
    }


}
