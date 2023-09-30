package com.example.android_intern

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_intern.databinding.PhoneFragmentBinding

private const val CALL = "tel:"

class PhoneFragment : Fragment() {

    private lateinit var binding: PhoneFragmentBinding
    private var phoneAdapter = PhoneAdapter() { phoneBook -> callNumber(phoneBook) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PhoneFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setPhoneBook()
        setListeners()
    }

    private fun setPhoneBook() {
        phoneAdapter.submitList(getInitNumbers())
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = phoneAdapter
    }

    private fun callNumber(position: PhoneBook) {
        val intent =
            Intent(Intent.ACTION_CALL, Uri.parse(CALL + position.phone))
        ContextCompat.startActivity(binding.root.context, intent, null)
    }

    private fun setListeners() {
        val filter = layoutInflater.inflate(R.layout.activity_main, null)
            .findViewById<EditText>(R.id.etInputFilter)
        filter.addTextChangedListener {
            val filteredList =
                getInitNumbers()
                    .filter { "${it.name} ${it.phone} ${it.type}".contains(filter.text) }
            phoneAdapter.submitList(filteredList)
        }
    }

}