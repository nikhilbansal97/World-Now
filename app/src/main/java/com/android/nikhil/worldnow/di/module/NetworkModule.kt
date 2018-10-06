package com.android.nikhil.worldnow.di.module

import com.android.nikhil.worldnow.data.source.remote.NewsRemoteDataSource
import com.android.nikhil.worldnow.data.source.repository.NewsRepository
import com.android.nikhil.worldnow.network.NewsInterface
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

  @Provides
  @Singleton
  fun getRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://content.guardianapis.com")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
  }

  @Provides
  @Singleton
  fun getClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()
  }

  @Provides
  @Singleton
  fun getLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor()
        .setLevel(BODY)
  }

  @Provides
  @Singleton
  fun getNewsInterface(retrofit: Retrofit): NewsInterface {
    return retrofit.create(NewsInterface::class.java)
  }

  @Provides
  @Singleton
  fun provideNewsRepository(newsApi : NewsInterface) : NewsRepository {
    return NewsRepository(NewsRemoteDataSource(newsApi))
  }
}