package com.example.lab_week_13.model

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.lab_week_13.R
import com.example.lab_week_13.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_RELEASE = "extra_release"
        const val EXTRA_OVERVIEW = "extra_overview"
        const val EXTRA_POSTER = "extra_poster"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)

        val title = intent.getStringExtra(EXTRA_TITLE)
        val releaseDate = intent.getStringExtra(EXTRA_RELEASE)
        val overview = intent.getStringExtra(EXTRA_OVERVIEW)
        val posterPath = intent.getStringExtra(EXTRA_POSTER)

        binding.textTitleDetail.text = title
        binding.textReleaseDate.text = "Released: $releaseDate"
        binding.textOverviewDetail.text = overview

        if (posterPath != null) {
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500$posterPath")
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.imagePosterDetail)
        }

        supportActionBar?.title = "Movie Details"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}