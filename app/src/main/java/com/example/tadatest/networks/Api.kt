package com.example.tadatest.networks

import com.example.tadatest.networks.reponses.PhotosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("photos")
    suspend fun getPhotos(@Query("sol") sol: Int, @Query("api_key") apiKey: String, @Query("page") page: Int): Response<PhotosResponse>
}