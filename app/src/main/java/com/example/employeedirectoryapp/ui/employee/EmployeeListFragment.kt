package com.example.employeedirectoryapp.ui.employee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.employeedirectoryapp.R
import com.example.employeedirectoryapp.databinding.FragmentEmployeeListBinding
import com.example.employeedirectoryapp.ui.adapter.EmployeeListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EmployeeListFragment : Fragment(R.layout.fragment_employee_list) {

    private lateinit var binding: FragmentEmployeeListBinding
    private val listAdapter: EmployeeListAdapter by lazy { EmployeeListAdapter() }
    private val viewModel: EmployeeViewModel by viewModels()

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

    override fun onStart() {
        super.onStart()
        viewModel.fetchEmployees()
    }

    private fun setupAdapters() {
        binding.employeeListRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.employeeListRecyclerView.adapter = listAdapter
        binding.swipeRefresh.setOnRefreshListener(viewModel.swipeRefreshListener)
    }


    private fun setupViewModelObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.employeesFlow.collect { employees ->
                listAdapter.submitList(employees)
            }
        }

        viewModel.swipeRefreshActive.observe(viewLifecycleOwner) { refreshing ->
            binding.swipeRefresh.isRefreshing = refreshing
        }
    }

}