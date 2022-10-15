package com.joseph.week4retrofit.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joseph.week4retrofit.model.MovieDetails
import com.joseph.week4retrofit.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.joseph.week4retrofit.model.Result
import kotlinx.coroutines.launch

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: MoviesRepository): ViewModel() {
    //get now playing data
    val _nowPlaying: MutableLiveData<ArrayList<Result>> by lazy {
        MutableLiveData<ArrayList<Result>>()
    }

    val nowPlaying: LiveData<ArrayList<Result>>get() = _nowPlaying

    fun getNowPlaying(apiKey: String, language: String, page: Int) = viewModelScope.launch {
        repository.getNowPlayingResults(apiKey, language, page).let {
            response ->
            if (response.isSuccessful){
                _nowPlaying.postValue(response.body()?.results as ArrayList<Result>?)
            }else{
                Log.e("Get Data", "Failed!")
            }
        }
    }

    //get movie detail
    val _movieDetails: MutableLiveData<MovieDetails> by lazy {
        MutableLiveData<MovieDetails>()
    }

    val movieDetails: LiveData<MovieDetails>get() = _movieDetails

    fun getMovieDetail(movieId:Int,apiKey: String) = viewModelScope.launch {
        repository.getMovieDetailResults(movieId, apiKey).let {
                response ->
            if (response.isSuccessful){
                _movieDetails.postValue(response.body() as MovieDetails)
            }else{
                Log.e("Get Movie Details Data", "Failed!")
            }
        }
    }
}