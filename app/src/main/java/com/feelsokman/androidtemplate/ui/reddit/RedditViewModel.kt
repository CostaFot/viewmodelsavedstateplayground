package com.feelsokman.androidtemplate.ui.reddit

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.launch

class RedditViewModel(
    private val redditRepository: RedditRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val redditPostData = MutableLiveData<RedditPost>()

    init {
        savedStateHandle.get<RedditPost>(KEY_REDDIT)?.let { state ->
            redditPostData.value = state
        }
    }

    fun getRedditPost() {
        viewModelScope.launch {
            redditPostData.value = RedditPost.Loading
            val post: RedditPost.Post = redditRepository.getPost()
            post.run {
                redditPostData.value = this
                savedStateHandle.set(KEY_REDDIT, this)
            }
        }
    }

    companion object {
        private const val KEY_REDDIT = "key_reddit"
    }
}
sealed class RedditPost {
    @Parcelize
    object Loading : RedditPost(), Parcelable

    @Parcelize
    data class Post(val text: String) : RedditPost(), Parcelable
}
