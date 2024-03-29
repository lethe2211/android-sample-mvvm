package com.example.samplemvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.samplemvvm.App
import com.example.samplemvvm.R
import com.example.samplemvvm.databinding.ListFragmentBinding
import com.example.samplemvvm.repository.RepoRepository
import com.example.samplemvvm.viewmodel.HomeViewModel
import com.example.samplemvvm.viewmodel.ListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
@ExperimentalCoroutinesApi
class ListFragment : Fragment() {

    // Use ViewModel KTX delegation property to instantiate ViewModel
    private val homeViewModel: HomeViewModel by activityViewModels() { HomeViewModel.Factory((requireActivity().application as App).appContainer.repoRepository, Dispatchers.Main) }
    private val viewModel: ListViewModel by viewModels()

    // Since the lifecycle of a View owned by a Fragment is usually longer than that of the Fragment itself
    // This will lead to a memory leak when the Fragment is destroyed.
    // Using this code, we can ensure the Binding object will be destroyed when the corresponding Fragment is destroyed.
    // We can prevent the memory leak as long as we modify _binding only in onCreateView or onDestroy
    private var _binding: ListFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate of DataBinding object should be done in onCreateView
        _binding = DataBindingUtil.inflate(inflater, R.layout.list_fragment, container, false)
        binding.homeViewModel = homeViewModel
        binding.viewModel = viewModel

        val recyclerView: RecyclerView = binding.listRecyclerView

        // Set the repo list as vertical list
        val layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.layoutManager = layoutManager

        adapter = ListAdapter()

        recyclerView.adapter = adapter

        // Add dividers to the repo list
        val itemDecoration = DividerItemDecoration(recyclerView.context, layoutManager.orientation)
        recyclerView.addItemDecoration(itemDecoration)

        // Observe repoList's change and update the list accordingly
        homeViewModel
            .repoList
            .onEach {
//                println(it)
                adapter.setRepoList(it)
            }
            .launchIn(lifecycleScope)

        homeViewModel
            .isLoading
            .onEach {
//                println("isLoading: ${homeViewModel.isLoading}")
                binding.isLoading = it
            }
            .launchIn(lifecycleScope)

        homeViewModel
            .isFailure
            .onEach {
//                println("isFailure: ${homeViewModel.isFailure}")
                binding.isFailure = it
            }
            .launchIn(lifecycleScope)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()

        // Ensure we erase the reference to the Binding object
        _binding = null
    }

}