package com.feelsokman.androidtemplate.ui.reddit

import com.feelsokman.androidtemplate.coroutine.DispatcherProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RedditRepository @Inject constructor(private val dispatcherProvider: DispatcherProvider) {

    suspend fun getPost(): RedditPost.Post = withContext(dispatcherProvider.io) {
        // ...imaginary operation that will take a while
        // ..
        delay(3000)
        RedditPost.Post("🐢 SLOW AND STEADY 🐢 WINS THE RACE 🐢 MODS CAN'T BAN ME 🐢 AT THIS PACE 🐢")
    }
}
