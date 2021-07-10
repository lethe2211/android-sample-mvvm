package com.example.samplemvvm.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.samplemvvm.R

class FormFragment : Fragment(R.layout.form_fragment) {

    companion object {
        fun newInstance() = FormFragment()
    }

    private lateinit var viewModel: FormViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FormViewModel::class.java)
        // TODO: Use the ViewModel
    }
}