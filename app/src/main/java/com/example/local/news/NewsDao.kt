package com.example.local.news

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.local.news.dbo.NewDbo

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(new: NewDbo)

    @Query("DELETE FROM NewDbo WHERE id == :newId")
    fun remove(newId: Long)

    @Query("SELECT * FROM NewDbo")
    fun getFavouriteNews(): List<NewDbo>
}