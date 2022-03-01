package com.example.tadatest.networks

import com.example.tadatest.networks.reponses.PhotosResponse
import retrofit2.Response

class ApiService(private val api: Api) {

    private var apiKey = "sQTomcFkpaFyB7f8JijeXKhYEcXaGjb66yKDZRRF"

    suspend fun getPhotos(sol: Int, page: Int): Response<PhotosResponse> {
        return api.getPhotos(sol, apiKey, page)
    }
}