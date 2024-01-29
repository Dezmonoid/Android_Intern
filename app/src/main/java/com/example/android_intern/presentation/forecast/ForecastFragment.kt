package com.example.android_intern.presentation.forecast

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_intern.R
import com.example.android_intern.databinding.WeatherFragmentBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForecastFragment : Fragment() {
    private val requestMultiplePermissions = requestPermission()
    private var _binding: WeatherFragmentBinding? = null
    private val viewModel by viewModels<ForecastViewModel>()
    private val binding get() = _binding!!
    private val adapter = ForecastAdapter { message: String ->
        shareMessage(message)
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
        requestMultiplePermissions.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    private fun requestPermission() =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (permissions.all { it.value }) {
                viewModel.getRegion()
                observeLiveData()
                observeRegion()
                observeEvent()
            } else {
                Snackbar.make(
                    requireView(),
                    getString(R.string.permission_denied),
                    Snackbar.LENGTH_LONG
                )
                    .show()
            }
        }

    private fun observeRegion() {
        viewModel.location.observe(viewLifecycleOwner) {locationName->
            binding.townText.text = locationName
        }
    }

    private fun observeLiveData() {
        viewModel.liveData.observe(viewLifecycleOwner) { forecast ->
            adapter.submitList(forecast)
        }
    }

    private fun observeEvent() {
        viewModel.event.observe(viewLifecycleOwner) {event->
            event.getContentEvent(requireView())
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

    private fun shareMessage(message: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT, binding.root.context.getString(
                    R.string.share, binding.townText.text
                ) + message
            )
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}