package com.example.tadatest.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.tadatest.base.BaseFragment
import com.example.tadatest.databinding.FragmentProfileBinding
import com.example.tadatest.prefs.PrefManager
import com.example.tadatest.ui.login.LoginActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {

    override fun getInjectViewModel(): ProfileViewModel = getViewModel()

    override fun getViewBinding() = FragmentProfileBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        renderUserImage()
        renderUserName()
        renderButtonLogout()
    }

    private fun renderUserImage() {
        Glide.with(requireContext())
            .load(urlUserImage)
            .into(binding.viewUserImage)
    }

    private fun renderUserName() {
        binding.textUserName.text = PrefManager(requireContext()).userName
    }

    private fun renderButtonLogout() {
        binding.buttonLogout.setOnClickListener {
            doLogout()
        }
    }

    private fun doLogout() {
        PrefManager(requireContext()).isLogin = false
        val intent = Intent(requireContext(), LoginActivity::class.java)
        requireContext().startActivity(intent)
        requireActivity().finishAffinity()
    }

    companion object {
        const val urlUserImage =
            "https://images.pexels.com/users/avatars/293608/oleg-magni-215.jpeg?auto=compress&fit=crop&h=256&w=256"
    }
}