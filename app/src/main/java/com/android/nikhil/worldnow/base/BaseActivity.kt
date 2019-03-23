package com.android.nikhil.worldnow.base

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity<VM: BaseViewModel> : DaggerAppCompatActivity() {

  lateinit var viewModel: VM

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel = ViewModelProviders.of(this).get(getViewModelClass())
  }

  abstract fun getViewModelClass(): Class<VM>

}