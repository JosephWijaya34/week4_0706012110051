package com.joseph.week4retrofit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.joseph.week4retrofit.adapter.NowPlayingAdapter
import com.joseph.week4retrofit.databinding.ActivityMainBinding
import com.joseph.week4retrofit.helper.Const
import com.joseph.week4retrofit.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewBind: ActivityMainBinding
    private lateinit var adapter: NowPlayingAdapter
    private lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBind.root)

        viewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)
        viewModel.getNowPlaying(Const.API_KEY, "en-US", 1)

        viewModel.nowPlaying.observe(this, Observer { response->
            viewBind.rvMain.layoutManager = LinearLayoutManager(this)
            adapter = NowPlayingAdapter(response)
            viewBind.rvMain.adapter = adapter
        })
    }
}