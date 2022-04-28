package com.example.employeedirectoryapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.employeedirectoryapp.R
import com.example.employeedirectoryapp.databinding.FragmentEmployeeListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeeListFragment : Fragment(R.layout.fragment_employee_list) {

    lateinit var binding: FragmentEmployeeListBinding
    private val listAdapter: EmployeeListAdapter by lazy { EmployeeListAdapter() }
    private val viewModel: EmployeeViewModel by navGraphViewModels(R.navigation.main_nav)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEmployeeListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapters()
        setupViewModelObservers()
    }

    private fun setupAdapters() {
        val context = binding.root.context
        binding.employeeListRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.employeeListRecyclerView.adapter = listAdapter
    }


    private fun setupViewModelObservers() {

    }

}