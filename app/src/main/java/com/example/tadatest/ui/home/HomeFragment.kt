package com.example.tadatest.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tadatest.MainActivity
import com.example.tadatest.R
import com.example.tadatest.base.BaseFragment
import com.example.tadatest.databinding.FragmentHomeBinding
import com.example.tadatest.models.Photo
import com.example.tadatest.models.Result
import com.example.tadatest.utils.InfiniteScrollListener
import com.example.tadatest.viewmodels.PhotosViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, PhotosViewModel>() {

    override fun getInjectViewModel(): PhotosViewModel = getViewModel()

    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBindings()
        initObservers()
        initViewModel()
    }

    private fun initBindings() {
        val linearLayoutManager = LinearLayoutManager(activity)
        binding.homePhotosList.run {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
        }
    }

    private fun initViewModel() {
        viewModel.loadData()
    }

    private fun initObservers() {
        viewModel.getPhotos().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.InProgress -> {
                    binding.homePhotosProgressContainer.visibility = View.VISIBLE
                    binding.homePhotosList.visibility = View.GONE
                }
                is Result.Success -> {
                    renderList(result.data)
                    binding.homePhotosProgressContainer.visibility = View.GONE
                    binding.homePhotosList.visibility = View.VISIBLE
                }
                is Result.Error -> {
                    binding.homePhotosProgressContainer.visibility = View.GONE
                    Toast.makeText(activity, result.exception.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun fetchMoreData() {
        viewModel.loadDataNextPage()
    }

    private fun renderList(photos: ArrayList<Photo>) {

        if (photos.isNotEmpty()) {
            if (viewModel.getCurrentPage() == 1 || binding.homePhotosList.adapter?.itemCount == 0) {
                setRecyclerData(photos)
            } else {
                if (binding.homePhotosList.adapter == null) { //after load more
                    setRecyclerData(photos)
                }
                binding.homePhotosList.adapter?.notifyDataSetChanged()
            }
            if (viewModel.listState != null) {
                binding.homePhotosList.layoutManager?.onRestoreInstanceState(viewModel.listState)
                viewModel.listState = null
            }
        } else {
            Toast.makeText(
                requireContext(),
                getString(R.string.msg_no_data_to_load),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun setRecyclerData(photos: ArrayList<Photo>) {
        with(binding.homePhotosList) {
            adapter = PhotosAdapter(requireContext(), photos.toList())
            addOnScrollListener(
                InfiniteScrollListener(
                    { fetchMoreData() },
                    layoutManager as LinearLayoutManager
                )
            )
        }
    }

    override fun onResume() {
        (activity as MainActivity).supportActionBar?.show()
        super.onResume()
    }

    override fun onDestroyView() {
        viewModel.listState = binding.homePhotosList.layoutManager?.onSaveInstanceState()
        super.onDestroyView()
    }
}