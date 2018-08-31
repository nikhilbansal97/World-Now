package com.android.nikhil.worldnow.di.component

import android.content.Context
import com.android.nikhil.worldnow.NewsApplication
import com.android.nikhil.worldnow.di.module.ActivityBindingModule
import com.android.nikhil.worldnow.di.module.NetworkModule
import com.android.nikhil.worldnow.di.module.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
  AndroidSupportInjectionModule::class,
  NetworkModule::class,
  ViewModelFactoryModule::class,
  ActivityBindingModule::class
])
interface AppComponent : AndroidInjector<NewsApplication> {

  @Component.Builder
  interface Builder {
    @BindsInstance fun context(context: Context): Builder
    fun build(): AppComponent
  }

}