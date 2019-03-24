package com.android.nikhil.worldnow.base

import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

open class BaseViewModel : ViewModel() {
  // Defines the background work that needs to be done.
  private val parentJob = Job()
  // Contains the information regarding the dispatcher and the job.
  private val coroutineContext = parentJob + Dispatchers.IO
  // Defines the lifecycle of the coroutines.
  val viewModelScope = CoroutineScope(context = coroutineContext)

  override fun onCleared() {
    /*
    * Cancel the job when the ViewModel is cleared.
    * When the job is cleared, all the child operations are cancelled.
    */
    parentJob.cancel()
    super.onCleared()
  }
}