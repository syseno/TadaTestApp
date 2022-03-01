package com.example.tadatest.viewmodels

import com.example.tadatest.ui.login.LoginViewModel
import com.example.tadatest.ui.profile.ProfileViewModel
import com.example.tadatest.ui.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PhotosViewModel(get()) }
    viewModel { LoginViewModel() }
    viewModel { RegisterViewModel() }
    viewModel { ProfileViewModel() }
}