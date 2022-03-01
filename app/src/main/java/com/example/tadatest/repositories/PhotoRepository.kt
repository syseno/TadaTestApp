package com.example.tadatest.repositories

import android.util.Log
import com.example.tadatest.models.Photo
import com.example.tadatest.models.Result
import com.example.tadatest.networks.ApiService
import retrofit2.HttpException

class PhotosRepository(private val apiService: ApiService) : BaseRepository() {

    companion object {
        private val TAG = PhotosRepository::class.java.name
    }

    suspend fun getPhotosFromApi(sol: Int, page: Int): Result<ArrayList<Photo>> {
        var result: Result<ArrayList<Photo>> = handleSuccess(arrayListOf())
        try {
            val response = apiService.getPhotos(sol, page)
            response.let { resultResponse ->
                resultResponse.body()?.photos?.let { photosResponse ->
                    result = handleSuccess(photosResponse)
                }
                resultResponse.errorBody()?.let {
                    result = handleException(resultResponse.code())
                }
            }
        } catch (error: HttpException) {
            error.message?.let { Log.d("$TAG - Error: ", it) }
            return handleException(error.code())
        }
        return result
    }

}