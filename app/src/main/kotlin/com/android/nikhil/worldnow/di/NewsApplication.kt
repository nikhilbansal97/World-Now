package com.android.nikhil.worldnow.di

import com.android.nikhil.worldnow.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.realm.Realm

class NewsApplication : DaggerApplication() {

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return DaggerAppComponent.builder()
      .context(this)
      .build()
  }

  override fun onCreate() {
    super.onCreate()
    Realm.init(this)
  }

}