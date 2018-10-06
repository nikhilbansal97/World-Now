package com.android.nikhil.worldnow.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.android.nikhil.worldnow.data.source.remote.NewsRemoteDataSource
import com.android.nikhil.worldnow.data.source.repository.NewsRepository
import com.android.nikhil.worldnow.di.qualifier.ViewModelKey
import com.android.nikhil.worldnow.ui.news.NewsViewModal
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Module
abstract class ViewModelFactoryModule {

  @Binds
  abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

  @Binds
  @IntoMap
  @ViewModelKey(NewsViewModal::class)
  abstract fun bindNewsViewModel(vm: NewsViewModal): ViewModel



}

class ViewModelFactory @Inject constructor(val viewModels: Map<Class<out ViewModel>,@JvmSuppressWildcards Provider<ViewModel>>) :
    ViewModelProvider.Factory {
  override fun <T : ViewModel?> create(modelClass: Class<T>): T = viewModels[modelClass]?.get() as T

}