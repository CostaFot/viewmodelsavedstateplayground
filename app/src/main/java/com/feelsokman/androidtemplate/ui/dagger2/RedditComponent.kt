package com.feelsokman.androidtemplate.ui.dagger2

import android.app.Activity
import com.feelsokman.androidtemplate.di.component.AppComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope

@Component(
    dependencies = [AppComponent::class]
)
@FeatureScope
interface RedditComponent {
    fun inject(redditActivity: RedditActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun activity(activity: Activity): Builder

        fun build(): RedditComponent
        fun appComponent(appComponent: AppComponent): Builder
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class FeatureScope
