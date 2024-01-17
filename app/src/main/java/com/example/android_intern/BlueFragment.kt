package com.example.android_intern

import androidx.navigation.fragment.findNavController

class BlueFragment : BaseFragment(R.layout.blue_fragment) {
    override fun onClick() = findNavController().navigate(R.id.redFragment)
}