package com.music.awesomemusic.di.modules

import com.music.awesomemusic.di.ActivityScope
import com.music.awesomemusic.functionalities.login.LoginActivity
import com.music.awesomemusic.functionalities.main.MainActivity
import com.music.awesomemusic.functionalities.sign_up.SignUpActivity
import com.music.awesomemusic.functionalities.start.StartActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeStartActivity(): StartActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributesLoginActivity(): LoginActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributesSignUpActivity(): SignUpActivity
}