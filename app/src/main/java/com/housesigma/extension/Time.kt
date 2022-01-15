package com.housesigma.extension

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun Long.toDateStr(pattern: String = "yyyy-MM-dd HH:mm:ss"): String {
    val date = Date(this*1000)
    val format = SimpleDateFormat(pattern)
    return format.format(date)
}

@SuppressLint("SimpleDateFormat")
fun String.toDateStr(pattern: String = "yyyy-MM-dd HH:mm:ss"): Long {
    val format = SimpleDateFormat(pattern)
    var tLong: Long = 0
    try {
        tLong = format.parse(this).time
    } catch (e: Exception) {
    }
    return tLong
}
