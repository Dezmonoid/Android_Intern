package com.example.android_intern.presentation.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_intern.App
import com.example.android_intern.data.ForecastRepositoryImpl
import com.example.android_intern.databinding.WeatherFragmentBinding
import com.example.android_intern.domain.ForecastRepository
import javax.inject.Inject

class ForecastFragment : Fragment() {
    private var _binding: WeatherFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter = ForecastAdapter()
    private val viewModel by viewModels<ForecastViewModel> {
        ForecastViewModelFactory(App.repository)
    }

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
        initRecyclerView()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.liveData.observe(viewLifecycleOwner) { forecast ->
            adapter.submitList(forecast)
        }
    }

    private fun initRecyclerView() {
        binding.weatherRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        binding.weatherRecyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}