package com.android.nikhil.worldnow.di.module

import com.android.nikhil.worldnow.service.NewsService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ServiceModule {

  private val realm_filename = "worldnews.realm"

  @Provides
  @Singleton
  fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://content.guardianapis.com")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .build()
  }

  @Provides
  @Singleton
  fun getClient(
    interceptor: HttpLoggingInterceptor
  ): OkHttpClient {
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
  fun getNewsService(retrofit: Retrofit): NewsService {
    return retrofit.create(NewsService::class.java)
  }

  @Provides
  fun getRealmObject(): Realm {
    val configuration = RealmConfiguration.Builder()
        .name(realm_filename)
        .build()
    return Realm.getInstance(configuration)
  }

}