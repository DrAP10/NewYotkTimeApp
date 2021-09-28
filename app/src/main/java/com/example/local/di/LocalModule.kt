package com.example.local.di

import android.content.Context
import com.example.local.AppRoomDatabase
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class LocalModule {

    @Provides
    @Singleton
    fun appRoomDatabaseProvider(context: Context) =
        AppRoomDatabase.buildDatabase(context)

    @Provides
    fun newsDaoProvider(database: AppRoomDatabase) =
        database.newsDao()


}