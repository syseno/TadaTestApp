package com.example.tadatest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.tadatest.models.Photo
import com.example.tadatest.models.Result
import com.example.tadatest.repositories.PhotosRepository
import com.example.tadatest.viewmodels.PhotosViewModel
import org.junit.Rule
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mock


class PhotosViewModelTest: KoinTest {
    val viewModelTest: PhotosViewModel by inject()
    val repository: PhotosRepository by inject()

    @Mock
    lateinit var listObserver: Observer<Result<ArrayList<Photo>>>

    @get:Rule
    val rule = InstantTaskExecutorRule()
}