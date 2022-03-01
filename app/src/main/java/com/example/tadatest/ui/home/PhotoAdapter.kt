package com.example.tadatest.ui.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tadatest.databinding.RowPhotoHomeBinding
import com.example.tadatest.models.Photo
import com.example.tadatest.ui.details.PhotoDetailActivity
import com.example.tadatest.utils.setImageFromUrlWithProgressBar

class PhotosAdapter(
    private val context: Context,
    private val photosList: List<Photo>
) :
    RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val itemBinding =
            RowPhotoHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotosViewHolder(itemBinding)
    }

    override fun getItemCount() = photosList.size

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(context, photosList[position])
    }

    class PhotosViewHolder(private val rowBinding: RowPhotoHomeBinding) :
        RecyclerView.ViewHolder(rowBinding.root) {

        fun bind(context: Context, photo: Photo) {
            rowBinding.photoBinding = photo
            rowBinding.rowPhotoRoverImg.setImageFromUrlWithProgressBar(
                photo.imgSrc,
                rowBinding.rowPhotoRoverProgress
            )
            rowBinding.root.setOnClickListener {
                val intent = Intent(context, PhotoDetailActivity::class.java)
                intent.putExtra(EXTRA_PHOTO, photo.imgSrc)
                intent.putExtra(EXTRA_CAMERA_FULL_NAME, photo.camera.fullName)
                intent.putExtra(EXTRA_CAMERA_NAME, photo.camera.name)
                intent.putExtra(EXTRA_ROVER_NAME, photo.rover.name)
                intent.putExtra(EXTRA_EARTH_DATE, photo.earthDate)
                context.startActivity(intent)
            }
        }
    }

    companion object {
        const val EXTRA_PHOTO = "extra_photo"
        const val EXTRA_CAMERA_FULL_NAME = "camera_full_name"
        const val EXTRA_CAMERA_NAME = "camera_name"
        const val EXTRA_ROVER_NAME = "rover_name"
        const val EXTRA_EARTH_DATE = "earth_date"
    }
}