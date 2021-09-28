package com.example.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.local.converter.DateConverter
import com.example.local.news.NewsDao
import com.example.local.news.dbo.NewDbo


@Database(
    entities = [
        NewDbo::class,
    ], version = 1, exportSchema = false
)
@TypeConverters(
    DateConverter::class,
)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {
        private const val DATABASE_NAME = "RoomDatabase.db"

        fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppRoomDatabase::class.java,
                DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
    }
}
