package com.example.parkcar
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

class ListPositionAdapter(private val context: Context, private val data: MutableList<Position>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var newView = convertView
        if (convertView == null)
            newView = LayoutInflater.from(context)
                .inflate(R.layout.position_adapter_layout, parent, false)
        if (newView != null) {


            val personSurname: ImageView = newView.findViewById(R.id.imageViewPinMaps)
            val personName: ImageView = newView.findViewById(R.id.imageViewShare)
            val personPos: ImageView = newView.findViewById(R.id.imageViewBin)

            // val personSurname: TextView = newView.findViewById(R.id.textView)

            Log.v("postClick", "Lista")
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