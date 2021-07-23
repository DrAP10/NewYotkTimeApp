package com.example

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.example.di.AppComponent
import com.example.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class NewYorkTimesApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    companion object {
        lateinit var instance: NewYorkTimesApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initComponent()
    }

    private fun initComponent() {
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }
}
