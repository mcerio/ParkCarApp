package com.example.parkcar

import android.content.Context
import android.icu.text.AlphabeticIndex.Record
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView


class ListPositionAdapter(private val context: Context, private val data: MutableList<Position>) : BaseAdapter() {
    lateinit var db:DataBaseHelper
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        db=DataBaseHelper(context)
        db.readData()
        var newView = convertView
        if (convertView == null)
            newView = LayoutInflater.from(context)
                .inflate(R.layout.position_adapter_layout, parent, false)
        if (newView != null) {


            val imagePinMaps: ImageView = newView.findViewById(R.id.imageViewPinMaps)
            val shareLogo: ImageView = newView.findViewById(R.id.imageViewShare)
            val binLogo: ImageView = newView.findViewById(R.id.imageViewBin)
            val indirizzo: TextView = newView.findViewById(R.id.indirizzo)
            val coordinate: TextView = newView.findViewById(R.id.coordinate)




            val dbList=db.readData()


            for(i in 0..(dbList.size)-1) {
                var lat=dbList[i].lat
                var long=dbList[i].long
                var addr=dbList[i].address

                indirizzo.text=addr
                coordinate.text="$lat,$long"
            }

            //val parts = data[position].split(" ")
            /* personSurname.text = parts[1]
             personName.text = parts[0]
             personPos.text =
                 "${position+1}"*/
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