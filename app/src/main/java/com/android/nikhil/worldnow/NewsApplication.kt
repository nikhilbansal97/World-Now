package com.android.nikhil.worldnow

import com.android.nikhil.worldnow.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class NewsApplication: DaggerApplication() {

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return DaggerAppComponent.builder()
              .context(this)
              .build()
  }

}