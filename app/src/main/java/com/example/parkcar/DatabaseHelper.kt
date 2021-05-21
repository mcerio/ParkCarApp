package com.example.parkcar

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast

val DATABASE_VERSION = 1
val DATABASE_NAME = "ParkCar.db"
val TABLENAME = "ParkingLots"
val ID_PARKING = "idParking"
val LAT = "latitude"
val LONG = "longitude"
val ADDRESS = "address"
lateinit var pos: Position


class DataBaseHelper(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLENAME + " (" +
                ID_PARKING + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                LAT + " NUMERIC," +
                LONG + " NUMERIC,"+
                ADDRESS + " VARCHAR(256))"

        db?.execSQL(createTable)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }


    fun insertData(pos: Position) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(LAT, pos.lat)
        contentValues.put(LONG, pos.long)
        contentValues.put(ADDRESS, pos.address)
        val result = database.insert(TABLENAME, null, contentValues)
        if (result == (0).toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
    }



    fun readData(): MutableList<Position> {
        val list: MutableList<Position> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $TABLENAME"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                var p = Position()
                p.id = result.getInt(result.getColumnIndex(ID_PARKING))
                p.lat = result.getDouble(result.getColumnIndex(LAT))
                p.long = result.getDouble(result.getColumnIndex(LONG))
                p.address = result.getString(result.getColumnIndex(ADDRESS))
                pos=p
                list.add(pos)
            } while (result.moveToNext())
        }
        return list
    }

    fun removeData() {

        val db = this.readableDatabase
        var posId=pos.id
        Log.v("ID","$posId //////////////////////////////////////////////////////////////////////////////////")
        val query = "Delete  from $TABLENAME where $ID_PARKING=$posId"
        val result = db.rawQuery(query, null)


    }

}