package com.example.tadatest.ui.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tadatest.databinding.ActivityPhotoDetailBinding
import com.example.tadatest.models.Photo
import com.example.tadatest.ui.home.PhotosAdapter.Companion.EXTRA_CAMERA_FULL_NAME
import com.example.tadatest.ui.home.PhotosAdapter.Companion.EXTRA_CAMERA_NAME
import com.example.tadatest.ui.home.PhotosAdapter.Companion.EXTRA_EARTH_DATE
import com.example.tadatest.ui.home.PhotosAdapter.Companion.EXTRA_PHOTO
import com.example.tadatest.ui.home.PhotosAdapter.Companion.EXTRA_ROVER_NAME
import com.example.tadatest.utils.setImageFromUrl
import com.example.tadatest.utils.setImageFromUrlWithProgressBar
import com.example.tadatest.utils.zoomImageFromThumb

class PhotoDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhotoDetailBinding
    private var photoUrl: String? = null
    private var cameraFullName: String? = null
    private var cameraName: String? = null
    private var roverName: String? = null
    private var earthDate: String? = null
    private var shortAnimationDuration: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPhotoDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intentProcess()

        binding.photoDetailsCameraFullName.text = cameraFullName
        binding.photoDetailsCameraName.text = cameraName
        binding.photoDetailsRoverName.text = roverName
        binding.photoDetailsPhotoDate.text = earthDate

        shortAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime)
        photoUrl?.let {
            binding.photoDetailsImageZoom.setImageFromUrl(it)
            with(binding.photoDetailsImage) {
                setImageFromUrlWithProgressBar(it, binding.photoDetailsProgress)
                setOnClickListener {
                    zoomImageFromThumb(
                        binding.photoDetailsImageZoom,
                        binding.photoDetailsContainer,
                        shortAnimationDuration
                    )
                }
            }
        }
    }

    private fun intentProcess() {
        photoUrl = intent.extras?.getString(EXTRA_PHOTO)
        cameraFullName = intent.extras?.getString(EXTRA_CAMERA_FULL_NAME)
        cameraName = intent.extras?.getString(EXTRA_CAMERA_NAME)
        roverName = intent.extras?.getString(EXTRA_ROVER_NAME)
        earthDate = intent.extras?.getString(EXTRA_EARTH_DATE)
    }

}