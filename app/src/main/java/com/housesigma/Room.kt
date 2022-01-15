package com.housesigma

import com.housesigma.extension.toDateStr
import java.text.DecimalFormat

class Room(
    val id:String,
    val up_date:String,
    val price:Float?,
    val location:Location,
    var address:String?,
    var photo:String?,
    var status:String?,
    var house_prop:RoomInfo
){
    fun getTimeStr():String{
       return  ""+(System.currentTimeMillis()/1000-up_date.toDateStr("yyyy-MM-dd")/1000)/86400+"   days";
    }

    fun getPriceStr():String{
        val df = DecimalFormat("#,###")
        return "C$"+df.format(price)
    }

    fun getBdsStr():String{
        return house_prop.bedroom.toString()+"bds"
    }

    fun getBathStr():String{
        return house_prop.bathroom.toString()+"ba"
    }

    fun getSqtStr():String{
        if( house_prop.sqft==0){
            return "--sqft"
        } else {
            return house_prop.sqft.toString()+"sqft"
        }
    }
}