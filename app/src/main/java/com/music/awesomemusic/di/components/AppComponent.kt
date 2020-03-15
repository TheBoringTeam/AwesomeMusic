package com.music.awesomemusic.di.components

import android.app.Application
import com.music.awesomemusic.AwesomeMusic
import com.music.awesomemusic.di.modules.ActivityModule
import com.music.awesomemusic.di.modules.AppModule
import com.music.awesomemusic.di.modules.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class, ActivityModule::class, AppModule::class, RepositoryModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: AwesomeMusic)
}