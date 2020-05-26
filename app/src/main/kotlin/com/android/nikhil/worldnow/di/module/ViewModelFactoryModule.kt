package com.android.nikhil.worldnow.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.android.nikhil.worldnow.di.qualifier.ViewModelKey
import com.android.nikhil.worldnow.news.list.NewsViewModal
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider

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