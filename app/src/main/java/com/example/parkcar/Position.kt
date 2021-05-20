package com.example.parkcar

data class Position(var lat: Double, var long: Double, var address:String){
    var id=-1
    constructor(): this(0.0, 0.0,"")

}
