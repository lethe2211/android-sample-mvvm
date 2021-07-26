package com.example.samplemvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.samplemvvm.App
import com.example.samplemvvm.R
import com.example.samplemvvm.databinding.FormFragmentBinding
import com.example.samplemvvm.databinding.HomeFragmentBinding
import com.example.samplemvvm.viewmodel.FormViewModel
import com.example.samplemvvm.viewmodel.HomeViewModel
import com.example.samplemvvm.viewmodel.ListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
@ExperimentalCoroutinesApi
class HomeFragment : Fragment() {

    // Shared ViewModel must be declared with Activity Scope (= activityViewModels)
    private val viewModel: HomeViewModel by activityViewModels() { HomeViewModel.Factory((requireActivity().application as App).appContainer.repoRepository) }

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }
}