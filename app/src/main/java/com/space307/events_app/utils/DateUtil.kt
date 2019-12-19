package com.space307.events_app.utils

import java.text.SimpleDateFormat
import java.util.*


fun getStringDate(timestamp: Long) : String {
    val df = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.getDefault())
    return df.format(Date(timestamp))
}