package com.feelsokman.androidtemplate.ui.dagger2

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.feelsokman.androidtemplate.R
import com.feelsokman.androidtemplate.RedditApplication
import com.feelsokman.androidtemplate.extensions.logDebug
import kotlinx.android.synthetic.main.activity_reddit.*
import javax.inject.Inject

class RedditActivity : AppCompatActivity() {
    @Inject
    internal lateinit var redditViewModelFactory: RedditViewModelFactory
    private val redditViewModel: RedditViewModel by viewModels { redditViewModelFactory }
    lateinit var redditComponent: RedditComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!::redditComponent.isInitialized) {
            logDebug { "Component is not initialised" }
            redditComponent = DaggerRedditComponent.builder()
                .activity(this)
                .appComponent(RedditApplication.component)
                .build()
        }
        redditComponent.inject(this)
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
}
