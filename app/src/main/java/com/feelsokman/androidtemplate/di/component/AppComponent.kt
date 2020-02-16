package com.feelsokman.androidtemplate.di.component

import android.app.Application
import com.feelsokman.androidtemplate.RedditApplication
import com.feelsokman.androidtemplate.coroutine.DispatcherProvider
import com.feelsokman.androidtemplate.di.module.AppModule
import com.feelsokman.androidtemplate.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        NetworkModule::class
    ]
)
interface AppComponent : AndroidInjector<RedditApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun dispatcherProvider(): DispatcherProvider
}
