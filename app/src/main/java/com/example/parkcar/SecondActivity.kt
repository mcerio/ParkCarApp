package com.example.parkcar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    private lateinit var db:DataBaseHelper
    private var addr: String=""
    private var long: Double=0.0
    private var lat: Double=0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        save_parking.setOnClickListener{

            db = DataBaseHelper(this)
            lat=intent.getDoubleExtra("lat",0.0)
            long=intent.getDoubleExtra("long",0.0)
            addr=intent.getStringExtra("addr")!!
            Log.v("POSISJDBUBI","$lat,$long $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$")
            var pos=Position(lat,long,addr)
            db.insertData(pos)

            val intent= Intent(this,MainActivity::class.java)

            startActivity(intent)

        }


    }
}