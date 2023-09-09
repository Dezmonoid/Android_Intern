package com.example.android_intern

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_intern.databinding.FragmentPhoneBinding

private const val CALL = "tel:"

class PhoneFragment : Fragment() {
    companion object {
        var phoneAdapter = PhoneAdapter()
        fun newInstance() = PhoneFragment()
    }

    private lateinit var binding: FragmentPhoneBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhoneBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        phoneAdapter.submitList(getInitNumbers())
        callSelectedNumber()
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = phoneAdapter
    }

    private fun callSelectedNumber() {
        phoneAdapter.setOnClickListener(object :
            PhoneAdapter.OnClickListener {
            override fun onClick(position: PhoneBook) {
                val intent =
                    Intent(Intent.ACTION_CALL, Uri.parse(CALL + position.phone))
                ContextCompat.startActivity(binding.root.context, intent, null)
            }
        })
    }
}