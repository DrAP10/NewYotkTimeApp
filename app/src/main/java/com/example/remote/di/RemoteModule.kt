package com.example.remote.di

import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class RemoteModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.nytimes.com/svc/mostpopular/v2/")
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(context: Context): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
            .addInterceptor(ChuckInterceptor(context))
            .followRedirects(false)
        return clientBuilder.build()
    }


}