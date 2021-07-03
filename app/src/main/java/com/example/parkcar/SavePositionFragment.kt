package com.example.parkcar

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.fragment_save_position.*


class SavePositionFragment : Fragment() {


    private var addr: String="AFRICA"
    private var long: Double=0.0
    private var lat: Double=0.0
    lateinit var db: DataBaseHelper
    private lateinit var fusedLocationClient: FusedLocationProviderClient




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fusedLocationClient= LocationServices.getFusedLocationProviderClient(requireContext())
        fetchLocation()






        return inflater.inflate(R.layout.fragment_save_position, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*button.setOnClickListener{
            db = DataBaseHelper(requireContext())
            /*TODO devo prendere i campi dal gps per inserirli nel db e sulla mappa

            *
            **/
            var pos=Position(lat,long,addr)
            db.insertData(pos)

            fragmentManager?.commit {
                setReorderingAllowed(true)
                replace<ListPositionFragment>(R.id.fragmentContainerView)
            }
        }*/

    }


    private fun fetchLocation() {
        val task= fusedLocationClient.lastLocation



        if(ActivityCompat.checkSelfPermission(requireContext(),android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(),android.Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireContext() as Activity,arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),101)
            return
        }

        task.addOnSuccessListener {

            if(it!=null){

                lat=it.latitude
                long=it.longitude
                Toast.makeText(requireContext(), "$lat,$long", Toast.LENGTH_LONG)

            }else{
                Toast.makeText(requireContext(), "NON VA ", Toast.LENGTH_LONG)
            }

        }

    }





}