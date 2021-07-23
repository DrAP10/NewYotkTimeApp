package com.example

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

const val NEWS_DATE_FORMAT = "yyyy-MM-dd"

object DateUtils {

    fun getNewsDateFormatted(date: String): Date? {
        return try {
            getNewsDateFormat().parse(date)
        } catch (e: ParseException) {
            null
        }
    }
    fun getNewsDateStringFormatted(date: Date?): String {
        return date?.let { getNewsDateFormat().format(it) } ?: kotlin.run { "" }
    }

    @Suppress("SimpleDateFormat")
    fun getNewsDateFormat(): SimpleDateFormat {
        return SimpleDateFormat(NEWS_DATE_FORMAT, Locale.getDefault())
    }
}
