package com.example.samplemvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.samplemvvm.R
import com.example.samplemvvm.domain.valueobject.RepoList

class ListFragment : Fragment(R.layout.list_fragment) {

    companion object {
        fun newInstance() = ListFragment()
    }

    private lateinit var viewModel: ListViewModel
    private lateinit var adapter: ListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)
            .get(ListViewModel::class.java)
        // TODO: Use the ViewModel

        setUpView(view)
    }

    private fun setUpView(view: View) {
        val recyclerView: RecyclerView = view.findViewById(R.id.listRecyclerView)

        // Set the repo list as vertical list
        val layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.layoutManager = layoutManager

        // TODO: It will be the value from the API
        adapter = ListAdapter(
            RepoList(
                listOf(
                    RepoList.Element(
                        "1",
                        "Hoge"
                    ),
                    RepoList.Element(
                        "2",
                        "Fuga"
                    ),
                    RepoList.Element(
                        "3",
                        "Piyo"
                    )
                )
            )
        )
        recyclerView.adapter = adapter

        // Add dividers to the repo list
        val itemDecoration = DividerItemDecoration(recyclerView.context, layoutManager.orientation)
        recyclerView.addItemDecoration(itemDecoration)
    }

}