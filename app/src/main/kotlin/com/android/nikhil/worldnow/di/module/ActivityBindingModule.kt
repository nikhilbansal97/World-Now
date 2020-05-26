package com.android.nikhil.worldnow.di.module

import com.android.nikhil.worldnow.news.list.NewsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

  @ContributesAndroidInjector
  abstract fun bindNewsActivity(): NewsActivity

}