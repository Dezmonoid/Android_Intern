package com.example.android_intern

import androidx.navigation.fragment.findNavController

class RedFragment : BaseFragment(R.layout.red_fragment) {
    override fun onClick() = findNavController().navigate(R.id.greenFragment)
}