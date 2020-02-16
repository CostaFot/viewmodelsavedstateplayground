package com.feelsokman.androidtemplate.ui.reddit

import android.app.Activity
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import javax.inject.Inject

class RedditViewModelFactory @Inject constructor(
    private val redditRepository: RedditRepository,
    activity: Activity
) : AbstractSavedStateViewModelFactory(activity as SavedStateRegistryOwner, null) {

    override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
        @Suppress("UNCHECKED_CAST")

        return RedditViewModel(redditRepository, handle) as T
    }
}
