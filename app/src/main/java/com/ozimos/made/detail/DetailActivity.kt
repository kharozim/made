package com.ozimos.made.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ozimos.core.domain.models.MovieDomain
import com.ozimos.core.presentation.util.loadImage
import com.ozimos.made.R
import com.ozimos.made.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var dataMovie: MovieDomain? = null

    private val viewmodel: DetailViewModel by viewModel()
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataMovie = intent.getParcelableExtra("detail")
        if (dataMovie != null) {
            setView()
        }

    }

    private fun setView() {
        binding.run {
            val image = getString(com.ozimos.core.R.string.url_poster, dataMovie?.image ?: "")
            isFavorite = dataMovie?.isFavorite ?: false
            ivBgPoster.loadImage(image)
            ivRoundPoster.loadImage(image)
            tvTitleDate.text = dataMovie?.name
            tvLanguage.text = dataMovie?.language
            tvPopularity.text = dataMovie?.popularity.toString()
            tvRating.text = dataMovie?.rating.toString()
            tvReleaseDate.text = dataMovie?.releaseDate
            tvOverview.text = dataMovie?.overview
            setupFavorite()

            fabFavorite.setOnClickListener {
                isFavorite = !isFavorite
                setupFavorite()
                viewmodel.setFavMovie(dataMovie?.id ?: 0, isFavorite)
            }

        }
    }

    private fun setupFavorite() {
        val backgroundColor =
            if (isFavorite) {
                R.color.red_500
            } else {
                R.color.white
            }
        binding.fabFavorite.imageTintList = this.getColorStateList(backgroundColor)
    }
}