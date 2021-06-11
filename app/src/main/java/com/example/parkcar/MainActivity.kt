package com.example.parkcar

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    private var addr: String="AFRICA"
    private var long: Double=0.0
    private var lat: Double=0.0
    //lateinit var db: DataBaseHelper
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationManager: LocationManager


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fusedLocationClient= LocationServices.getFusedLocationProviderClient(this)

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager;
        new_park.setOnClickListener{
            fetchLocation()

            /*fragmentManager?.commit {
                setReorderingAllowed(true)
                replace<SavePositionFragment>(R.id.fragmentContainerView)*/

            val intent= Intent(this,SecondActivity::class.java)
            intent.putExtra("lat",lat)
            intent.putExtra("long",long)
            intent.putExtra("addr",addr)

            startActivity(intent)

        }
    }
    private fun fetchLocation() {
        val task= fusedLocationClient.lastLocation

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

        }
        val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)



        //if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)
        //    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)
        //    != PackageManager.PERMISSION_GRANTED){
        //    ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),101)
        //    return
        //}

            if(location!=null){


                    val lat=location.longitude
                    val long=location.latitude
                    Toast.makeText(this, "$lat,$long", Toast.LENGTH_LONG).show()



            }else{
                Toast.makeText(this, "NON VA ", Toast.LENGTH_LONG).show()
            }



    }


}