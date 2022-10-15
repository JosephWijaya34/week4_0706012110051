package com.joseph.week4retrofit.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.joseph.week4retrofit.adapter.GenreAdapter
import com.joseph.week4retrofit.adapter.ProductionCompanyAdapter
import com.joseph.week4retrofit.adapter.SpokenLanguageAdapter
import com.joseph.week4retrofit.databinding.ActivityMovieDetailBinding
import com.joseph.week4retrofit.helper.Const
import com.joseph.week4retrofit.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetail : AppCompatActivity() {

    private lateinit var viewBind: ActivityMovieDetailBinding
    private lateinit var adapterGenre: GenreAdapter
    private lateinit var adapterPCompany: ProductionCompanyAdapter
    private lateinit var adapterSLanguage: SpokenLanguageAdapter
    private lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind=ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(viewBind.root)

        val movieId=intent.getIntExtra("movie_id", 0)
        Toast.makeText(applicationContext, "Movie ID: ${movieId}", Toast.LENGTH_SHORT).show()

        viewModel=ViewModelProvider(this)[MoviesViewModel::class.java]
        viewModel.getMovieDetail(movieId, Const.API_KEY)

        viewBind.backgroundDetail.visibility=View.VISIBLE

        viewModel.movieDetails.observe(this) { response ->
            viewBind.backgroundDetail.visibility=View.INVISIBLE

            //recycle view buat genre
            viewBind.rvGenre.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            adapterGenre = GenreAdapter(response.genres)
            viewBind.rvGenre.adapter = adapterGenre

            //recycle view buat Production Company
            viewBind.rvPCompany.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            adapterPCompany = ProductionCompanyAdapter(response.production_companies)
            viewBind.rvPCompany.adapter = adapterPCompany

            //recycle view buat Spoken Language
            viewBind.rvSpokenLanguage.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            adapterSLanguage = SpokenLanguageAdapter(response.spoken_languages)
            viewBind.rvSpokenLanguage.adapter = adapterSLanguage

            viewBind.tvOverview.apply {
                text=response.overview
            }

            viewBind.tvTitleMovieDetail.apply {
                text=response.title
            }
            Glide.with(applicationContext)
                .load(Const.IMG_URL + response.backdrop_path)
                .into(viewBind.imgPosterMovieDetail)

        }

    }
}