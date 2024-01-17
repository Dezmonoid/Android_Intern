package com.example.android_intern.presentation.forecast

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_intern.App
import com.example.android_intern.databinding.WeatherFragmentBinding

class ForecastFragment : Fragment() {
    private var _binding: WeatherFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter = ForecastAdapter()
    private lateinit var viewModel: ForecastViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = WeatherFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initForecastViewModel()
        initCharacterRecyclerView()
        observeLiveData()
    }

    private fun observeLiveData() {
        Log.e("observe","compl")
        viewModel.liveData.observe(viewLifecycleOwner) { forecast ->
            adapter.submitList(forecast)
        }
    }

    private fun initCharacterRecyclerView() {
        Log.e("InitRecycler","compl")
        binding.weatherRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        binding.weatherRecyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initForecastViewModel() {
        Log.e("InitViewModel","compl")
        viewModel = ViewModelProvider(
            this,
            ForecastFactory(App.repository)
        )[ForecastViewModel::class.java]
    }
}