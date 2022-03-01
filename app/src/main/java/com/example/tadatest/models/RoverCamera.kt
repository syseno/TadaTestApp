package com.example.tadatest.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RoverCamera(
    @SerializedName("full_name") val fullName: String,
    @SerializedName("name") val name: String
) : Parcelable