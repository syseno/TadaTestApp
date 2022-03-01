package com.example.tadatest.viewmodels

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tadatest.models.Photo
import com.example.tadatest.models.Result
import com.example.tadatest.repositories.PhotosRepository
import kotlinx.coroutines.launch

class PhotosViewModel(private val photosRepository: PhotosRepository) : ViewModel() {

    private val photos = MutableLiveData<Result<ArrayList<Photo>>>()
    private var currentPage = 1
    var listState: Parcelable? = null //to save and restore rv's adapter

    fun getPhotos() = photos

    fun getCurrentPage() = currentPage

    fun loadData() {
        if (currentPage == 1) {
            photos.postValue(Result.InProgress)
        }
        viewModelScope.launch {
            val response = photosRepository.getPhotosFromApi(1000, currentPage)
            response.let {
                when (it) {
                    is Result.Success -> {
                        val photosList = it.extractData
                        photosList?.let { list ->
                            if (currentPage == 1) { //set photos for first page
                                photos.postValue(Result.Success(list))
                            } else { //add photos to current list
                                val currentPhotos: ArrayList<Photo>? = photos.value?.extractData
                                if (currentPhotos == null || currentPhotos.isEmpty()) {
                                    photos.postValue(Result.Success(list))
                                } else {
                                    currentPhotos.addAll(list)
                                    photos.postValue(Result.Success(currentPhotos))
                                }
                            }
                        }
                    }
                    else -> photos.postValue(it)
                }
            }
        }
    }

    fun loadDataNextPage() {
        currentPage++
        loadData()
    }
}