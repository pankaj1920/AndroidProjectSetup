package com.applaunch.appbase.utils_base

import android.os.Build
import androidx.annotation.RequiresApi

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

val SIMPE_TIME = "hh:mm"
val SERVER_TIME_FORMAT = "EE MMM dd HH:mm:ss z yyyy"



@RequiresApi(Build.VERSION_CODES.O)
fun String.getUtcDateTime(): Date {
    val instant = LocalDateTime.parse(this, DateTimeFormatter.ISO_DATE_TIME)
        .atZone(ZoneId.of("UTC")) //the current zone is UTC
        .withZoneSameInstant(ZoneId.systemDefault()) //the new time zone
        .toInstant();

    val date = Date.from(instant)
    return date
}


@RequiresApi(Build.VERSION_CODES.O)
fun String.getUtcTime(): String {
    val curFormatter = SimpleDateFormat(SERVER_TIME_FORMAT, Locale.ENGLISH)
    val timeFormatter = SimpleDateFormat(SIMPE_TIME)
    val time = curFormatter.parse(this.getUtcDateTime().toString())
    return timeFormatter.format(time)
}







