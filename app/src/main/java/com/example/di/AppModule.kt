package com.example.di

import android.app.Application
import android.content.Context
import com.example.NewYorkTimesApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideApplicationContext(app: NewYorkTimesApp): Context = app

    @Provides
    @Singleton
    fun provideApplication(app: NewYorkTimesApp): Application = app

}