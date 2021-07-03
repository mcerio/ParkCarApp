package com.example.parkcar

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity


class ListPositionAdapter(private val context: Context, private val data: MutableList<Position>) : BaseAdapter() {
    lateinit var db:DataBaseHelper
    lateinit var dbList: MutableList<Position>
    lateinit var imagePinMaps: ImageView
    lateinit var shareLogo: ImageView
    lateinit var binLogo: ImageView
    lateinit var indirizzo: TextView
    lateinit var coordinate: TextView
    lateinit var pos:Position
    var id:Int=0
    var lat:Double=0.0
    var long:Double=0.0
    var addr=""


    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        db=DataBaseHelper(context)

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




            dbList=db.readData()


            id=(dbList[position].id)
            println(id)
            lat=dbList[position].lat
            long=dbList[position].long
            addr=dbList[position].address
            pos=Position(lat,long,addr)
            indirizzo.text=addr
            coordinate.text="$lat,$long"

            binLogo.setOnClickListener{


                val builder = AlertDialog.Builder(context)

                builder.setMessage("Are you sure?")

                    .setCancelable(false)

                    .setPositiveButton("Ok") { _, _ ->

                        // Deleting the selected parking from the sqlite database

                        db.removeData(dbList[position].id)
                        dbList.removeAt(position)
                        notifyDataSetChanged()


                        val confirmation = AlertDialog.Builder(context)

                        confirmation.setMessage("Cancel")

                            .setCancelable(false)

                            .setPositiveButton("Ok") { dialog, _ ->






                                // Dismissing the dialog

                                dialog.dismiss()

                            }

                        confirmation.create().show()

                    }

                    .setNegativeButton("Cancel") { dialog, _ ->

                        // Dismissing the dialog

                        dialog.dismiss()

                    }



                builder.create().show()


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




        newView?.setOnClickListener {
            val intent = Intent(context, OpenPositionActivity::class.java)
            intent.putExtra("lat", dbList[position].lat)

            intent.putExtra("long", dbList[position].long)


            context.startActivity(intent)
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