package com.music.awesomemusic.di.modules

import com.music.awesomemusic.data.repository.AccountApiService
import com.music.awesomemusic.utils.ServiceGenerator
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class RepositoryModule {
    //  private val baseUrl = "http://10.0.2.2:8080"
    private val baseUrl = "http://34.90.124.7:8082"

    @Provides
    @Singleton
    fun providesUserRepository(): AccountApiService {
        //TODO: Clould be optimized via
        return ServiceGenerator.createService(AccountApiService::class.java)
    }
}