package com.example.parkcar

import android.content.Intent
import android.os.Bundle


import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.fragment.app.replace

import kotlinx.android.synthetic.main.fragment_list_position.*




class ListPositionFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment



        return inflater.inflate(R.layout.fragment_list_position, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val db = DataBaseHelper(requireContext())
        var pos = db.readData()

        val adapter=ListPositionAdapter(requireContext(), pos)
        list_view.adapter = adapter
        list_view.emptyView=noParkingTextView


        /*new_park.setOnClickListener{
            /*fragmentManager?.commit {
                setReorderingAllowed(true)
                replace<SavePositionFragment>(R.id.fragmentContainerView)*/

            val intent= Intent(requireContext(),SecondActivity::class.java)
            startActivity(intent)

        }*/
    }
}