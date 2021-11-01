package com.example.di

import com.example.NewYorkTimesApp
import com.example.domain.di.NewsUseCaseModule
import com.example.local.di.LocalModule
import com.example.local.news.di.NewsLocalModule
import com.example.remote.news.di.NewsRemoteModule
import com.example.remote.di.RemoteModule
import com.example.repository.news.di.NewsRepositoryModule
import com.example.ui.di.NewsFragmentBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        RemoteModule::class,
        LocalModule::class,
        NewsRemoteModule::class,
        NewsLocalModule::class,
        NewsRepositoryModule::class,
        NewsUseCaseModule::class,
        NewsFragmentBuilder::class,
    ]
)

@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: NewYorkTimesApp): Builder

        fun build(): AppComponent
    }

    fun inject(app: NewYorkTimesApp)
}