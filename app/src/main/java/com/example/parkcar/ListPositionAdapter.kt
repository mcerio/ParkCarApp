package com.example.parkcar

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity


class ListPositionAdapter(private val context: Context, private val data: MutableList<Position>) : BaseAdapter() {
    lateinit var db:DataBaseHelper
    lateinit var imagePinMaps: ImageView
    lateinit var shareLogo: ImageView
    lateinit var binLogo: ImageView
    lateinit var indirizzo: TextView
    lateinit var coordinate: TextView
    lateinit var pos:Position
    var lat:Double=0.0
    var long:Double=0.0
    var addr=""


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        db=DataBaseHelper(context)
        db.readData()
        var newView = convertView
        if (convertView == null)
            newView = LayoutInflater.from(context)
                .inflate(R.layout.position_adapter_layout, parent, false)
        if (newView != null) {


             imagePinMaps= newView.findViewById(R.id.imageViewPinMaps)
             shareLogo= newView.findViewById(R.id.imageViewShare)
             binLogo = newView.findViewById(R.id.imageViewBin)
             indirizzo = newView.findViewById(R.id.indirizzo)
             coordinate = newView.findViewById(R.id.coordinate)




            val dbList=db.readData()


            for(i in 0..(dbList.size)-1) {
                 lat=dbList[i].lat
                 long=dbList[i].long
                 addr=dbList[i].address
                 pos=Position(lat,long,addr)
                indirizzo.text=addr
                coordinate.text="$lat,$long"
            }


        }


        shareLogo.setOnClickListener{


            val uri = "http://maps.google.com/maps?saddr=$lat,$long"

            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            val ShareSub = "Here is my location"
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, ShareSub)
            sharingIntent.putExtra(Intent.EXTRA_TEXT, uri)

            context.startActivity(Intent.createChooser(sharingIntent, "Share via"))
        }


        binLogo.setOnClickListener{

            //TODO cancello parcheggio
            db.removeData()
            notifyDataSetChanged()
            notifyDataSetInvalidated()


        }

        return newView
    }



    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.size
    }
}