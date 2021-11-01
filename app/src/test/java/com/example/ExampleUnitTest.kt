package com.example

import com.example.model.news.bo.NewBo
import com.example.remote.news.dto.NewDto
import com.example.remote.news.toBo
import org.junit.Test

import org.junit.Assert.*
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun newBo_equal() {
        val now = Date()
        val favouriteNew = NewBo(
            1L,
            "title",
            "author",
            "sports",
            now,
            null,
            null,
            true
        )
        val equalButNonFavouriteNew = NewBo(
            1L,
            "title",
            "author",
            "sports",
            now,
            null,
            null,
            false
        )
        assertTrue(favouriteNew == equalButNonFavouriteNew)
    }

}