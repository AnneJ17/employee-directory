package com.example.employeedirectoryapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.employeedirectoryapp.R
import com.example.employeedirectoryapp.databinding.ItemViewListBinding
import com.example.employeedirectoryapp.model.Employee

class EmployeeListAdapter : ListAdapter<Employee, EmployeeListAdapter.EmployeeViewHolder>(
    DIFF_CALLBACK
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_list, parent, false)
        return EmployeeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            listItemTitleText.text = item.full_name
            listItemSubtitleText.text = item.team
            listItemIconLeft.load(item.photo_url_small)
        }
    }

    inner class EmployeeViewHolder(
        private val rootView: View,
        val binding: ItemViewListBinding = ItemViewListBinding.bind(rootView)
    ) : RecyclerView.ViewHolder(rootView)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Employee>() {
            override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
                return oldItem.uuid == newItem.uuid
            }

            override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
                return oldItem == newItem
            }
        }
    }
}