package com.example.samplemvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.samplemvvm.App
import com.example.samplemvvm.R
import com.example.samplemvvm.databinding.FormFragmentBinding
import com.example.samplemvvm.viewmodel.FormViewModel
import com.example.samplemvvm.viewmodel.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
@ExperimentalCoroutinesApi
class FormFragment : Fragment() {

    private val homeViewModel: HomeViewModel by activityViewModels() { HomeViewModel.Factory((requireActivity().application as App).appContainer.repoRepository, Dispatchers.Main) }
    private val viewModel: FormViewModel by viewModels()

    private var _binding: FormFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.form_fragment, container, false)
        binding.homeViewModel = homeViewModel
        binding.viewModel = viewModel
        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            /**
             * Callback of a submit event.
             */
            override fun onQueryTextSubmit(query: String?): Boolean {
//                println("onQueryTextSubmit: ${query}")
                homeViewModel.submit()
                return false
            }

            /**
             * Callback of a text change event.
             */
            override fun onQueryTextChange(newText: String?): Boolean {
                homeViewModel.setUserName(newText.orEmpty())
//                println("onQueryTextChange: ${newText}")
                return false
            }
        })

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }
}