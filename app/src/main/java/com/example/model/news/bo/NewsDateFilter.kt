package com.example.model.news.bo


private const val ONE_DAY_POS = 0
private const val SEVEN_DAYS_POS = 1
private const val THIRTY_DAYS_POS = 2

enum class NewsDateFilter(val position: Int) {
    ONE_DAY(ONE_DAY_POS),
    SEVEN_DAYS(SEVEN_DAYS_POS),
    THIRTY_DAYS(THIRTY_DAYS_POS);

    fun getDateSearchInt(): Int {
        return when (this) {
            ONE_DAY -> 1
            SEVEN_DAYS -> 7
            THIRTY_DAYS -> 30
        }
    }

    companion object {

        fun fromId(value: Int?): NewsDateFilter {
            for (event in values()) {
                if (event.position == value) {
                    return event
                }
            }
            return ONE_DAY
        }

    }
}