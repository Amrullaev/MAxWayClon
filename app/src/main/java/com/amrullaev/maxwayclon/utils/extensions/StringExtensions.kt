package com.amrullaev.maxwayclon.utils.extensions

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*



@SuppressLint("SimpleDateFormat")
fun getCurrentDate(): String {
    val c = Calendar.getInstance().time
    return SimpleDateFormat("MMM dd,yyyy").format(c).uppercase()
}

@SuppressLint("SimpleDateFormat")
fun getCurrentDate(date: Date): String {
    return SimpleDateFormat("MMM dd,yyyy").format(date).uppercase()
}

@SuppressLint("SimpleDateFormat")
fun String.toDate(): Date {
    val format = SimpleDateFormat("MMM dd,yyyy")
    format.parse(this)
    return format.calendar.time
}


