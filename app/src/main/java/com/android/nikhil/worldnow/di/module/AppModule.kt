package com.android.nikhil.worldnow.di.module

import com.android.nikhil.worldnow.network.NewsInterface
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

  @Provides
  @Singleton
  fun getRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://content.guardianapis.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
  }

  @Provides
  @Singleton
  fun getNewsInterface(retrofit: Retrofit): NewsInterface {
    return retrofit.create(NewsInterface::class.java)
  }
}