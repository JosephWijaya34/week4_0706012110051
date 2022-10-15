package com.joseph.week4retrofit.repository

import com.joseph.week4retrofit.retrofit.EndPointApi
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val api: EndPointApi) {
    suspend fun getNowPlayingResults(apiKey: String, language: String, page: Int)=
        api.getNowPlaying(apiKey, language, page)

    suspend fun getMovieDetailResults(movieId: Int, apiKey: String)=api.getMovieDetails(movieId, apiKey)
}