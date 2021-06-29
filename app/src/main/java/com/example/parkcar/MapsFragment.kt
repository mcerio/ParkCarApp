package com.example.parkcar

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsFragment : Fragment() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    var lat:Double=0.0
    var long:Double=0.0
    lateinit var db:DataBaseHelper
    var address=""
    lateinit var pos:Position


    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */

        val current_loc = LatLng(lat, long)
        googleMap.addMarker(MarkerOptions().position(current_loc).title(this.resources.getText(R.string.tag_position).toString()))
        val cameraPosition = CameraPosition.Builder().target(current_loc).zoom(14.0f).build()
        val cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition)
        googleMap.moveCamera(cameraUpdate)
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(current_loc))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        /*
        db=DataBaseHelper(requireContext())
        fusedLocationClient=LocationServices.getFusedLocationProviderClient(requireContext())

        fetchLocation()*/
        lat=requireActivity().intent.getDoubleExtra("lat",0.0)
        long=requireActivity().intent.getDoubleExtra("long",0.0)
        //address=requireActivity().intent.getStringExtra("addr")!!


        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }



    /*private fun fetchLocation() {
        val task= fusedLocationClient.lastLocation



        if(ActivityCompat.checkSelfPermission(requireContext(),android.Manifest.permission.ACCESS_FINE_LOCATION)
            !=PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(),android.Manifest.permission.ACCESS_COARSE_LOCATION)
            !=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireContext() as Activity,arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),101)
            return
        }

        task.addOnSuccessListener {

            if(it!=null){

                lat=it.latitude
                long=it.longitude
                //pos= Position(lat,long,address)
                //db.insertData(pos)

            }
        }
    }*/
}