package com.example.tadatest.networks.reponses


import com.example.tadatest.models.Photo
import com.google.gson.annotations.SerializedName

data class PhotosResponse(@SerializedName("photos") val photos: ArrayList<Photo>)