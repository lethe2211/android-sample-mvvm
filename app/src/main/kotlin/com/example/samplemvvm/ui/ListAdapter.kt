package com.example.samplemvvm.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.samplemvvm.R
import com.example.samplemvvm.domain.valueobject.RepoList

class ListAdapter() : RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    private var repoList = RepoList(listOf())

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val repoTitleTextView: TextView
        val repoDescriptionTextView: TextView

        init {
            repoTitleTextView = view.findViewById(R.id.repo_title)
            repoDescriptionTextView = view.findViewById(R.id.repo_description)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.repoTitleTextView.text = repoList[position].title
        holder.repoDescriptionTextView.text = repoList[position].description
    }

    override fun getItemCount(): Int {
        return repoList.size()
    }

    fun setRepoList(repoList: RepoList) {
        this.repoList = repoList
        notifyDataSetChanged()
    }
}