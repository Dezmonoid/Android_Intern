package com.example.android_intern

import androidx.navigation.fragment.findNavController

class GreenFragment : BaseFragment(R.layout.green_fragment) {
    override fun onClick() = findNavController().navigate(R.id.blueFragment)
}