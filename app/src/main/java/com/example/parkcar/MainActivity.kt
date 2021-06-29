package com.example.parkcar

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(){

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient // Fused location client to provide the current location
    private lateinit var db: DataBaseHelper
    private var parkingList: MutableList<Position> = ArrayList()
    private lateinit var adapter: ListPositionAdapter
    private var latitude: Double = 0.0 // Latitude of the current location initialized
    private var longitude: Double = 0.0 // Longitude of the current location initialized

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize the location provider client
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        // Inflate the layout for this fragment
        setContentView(R.layout.activity_main)




        // Getting the current location (long - lat)
        val locationTask = if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 0)
            fusedLocationProviderClient.lastLocation
        } else {
            fusedLocationProviderClient.lastLocation
        }

        locationTask.addOnSuccessListener {
            latitude = it?.latitude ?: 0.0
            longitude = it?.longitude ?: 0.0

        }



        new_park.setOnClickListener {
            // Starting the new activity to show the countdown and the maps
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("lat", latitude)
            intent.putExtra("long", longitude)

            //acquisisco la località
            val geocoder = Geocoder(this, Locale.getDefault())

            val addr: List<Address> = geocoder.getFromLocation(latitude, longitude, 1)

            // The current location

            val location = addr[0].getAddressLine(0)
            intent.putExtra("addr",location)
            startActivity(intent)
        }
    }





}