package com.android.nikhil.worldnow.di.module

import com.android.nikhil.worldnow.ui.news.NewsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

  @ContributesAndroidInjector
  abstract fun bindNewsActivity(): NewsActivity

}