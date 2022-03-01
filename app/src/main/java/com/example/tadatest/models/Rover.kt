package com.example.tadatest.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rover(@SerializedName("cameras") val roverCameras: List<RoverCamera> = arrayListOf(),
                 @SerializedName("id") val id: Int,
                 @SerializedName("landing_date") val landingDate: String,
                 @SerializedName("launch_date") val launchDate: String,
                 @SerializedName("max_date") val maxDate: String,
                 @SerializedName("max_sol") val maxSol: Int,
                 @SerializedName("name") val name: String,
                 @SerializedName("status") val status: String,
                 @SerializedName("total_photos") val totalPhotos: Int) : Parcelable