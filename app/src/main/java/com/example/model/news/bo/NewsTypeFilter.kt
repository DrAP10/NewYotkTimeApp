package com.example.model.news.bo


private const val MOST_MAILED_POS = 0
private const val MOST_SHARED_POS = 1
private const val MOST_VIEWED_POS = 2

enum class NewsTypeFilter(val position: Int) {
    MOST_MAILED(MOST_MAILED_POS),
    MOST_SHARED(MOST_SHARED_POS),
    MOST_VIEWED(MOST_VIEWED_POS);

    fun getTypeSearchString(): String {
        return when (this) {
            MOST_MAILED -> "mostemailed"
            MOST_SHARED -> "mostshared"
            MOST_VIEWED -> "mostviewed"
        }
    }

    companion object {

        fun fromId(value: Int?): NewsTypeFilter {
            for (event in values()) {
                if (event.position == value) {
                    return event
                }
            }
            return MOST_MAILED
        }

    }
}