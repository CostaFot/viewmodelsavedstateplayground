package com.feelsokman.androidtemplate.ui.reddit

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.feelsokman.androidtemplate.R
import com.feelsokman.androidtemplate.RedditApplication
import kotlinx.android.synthetic.main.activity_reddit.*
import javax.inject.Inject

class RedditActivity : AppCompatActivity() {
    @Inject
    internal lateinit var redditViewModelFactory: RedditViewModelFactory
    private val redditViewModel: RedditViewModel by viewModels { redditViewModelFactory }
    lateinit var redditComponent: RedditComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
        setContentView(R.layout.activity_reddit)

        redditViewModel.redditPostData.observe(this) { state ->
            when (state) {
                RedditPost.Loading -> button.text = "Loading"
                is RedditPost.Post -> button.text = state.text
            }
        }

        button.setOnClickListener {
            redditViewModel.getRedditPost()
        }
    }

    private fun injectDependencies() {
        if (!::redditComponent.isInitialized) {
            redditComponent = DaggerRedditComponent.builder()
                .activity(this@RedditActivity)
                .appComponent(RedditApplication.component)
                .build()
        }
        redditComponent.inject(this)
    }
}
