package com.android.nikhil.worldnow.base

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

  var compositeDisposable = CompositeDisposable()

  override fun onCleared() {
    super.onCleared()
    compositeDisposable.dispose()
    compositeDisposable.clear()
  }

  protected fun addDisposable(disposable: Disposable) {
    compositeDisposable.add(disposable)
  }

}