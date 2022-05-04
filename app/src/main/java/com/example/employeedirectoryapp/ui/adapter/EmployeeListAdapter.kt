package com.example.employeedirectoryapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.employeedirectoryapp.R
import com.example.employeedirectoryapp.databinding.ItemViewHeaderBinding
import com.example.employeedirectoryapp.databinding.ItemViewListBinding
import com.example.employeedirectoryapp.model.EmployeeGroupedItem

class EmployeeListAdapter : ListAdapter<EmployeeGroupedItem, RecyclerView.ViewHolder>(
    DIFF_CALLBACK
) {

    override fun getItemViewType(position: Int): Int {
        return when (val item = getItem(position)) {
            is EmployeeGroupedItem.Header -> VIEW_TYPE_HEADER
            is EmployeeGroupedItem.Employee -> VIEW_TYPE_EMPLOYEE
            else -> error("Unsupported element at position $position - (${item.javaClass.simpleName})")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> HeaderViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_view_header, parent, false)
            )
            VIEW_TYPE_EMPLOYEE -> EmployeeViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_view_list, parent, false)
            )
            else -> error("Unsupported view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> bindHeaderViewHolder(holder, position)
            is EmployeeViewHolder -> bindEmployeeViewHolder(holder, position)
        }
    }

    private fun bindHeaderViewHolder(holder: HeaderViewHolder, position: Int) {
        val item = getItem(position) as EmployeeGroupedItem.Header
        holder.binding.headerTextView.text = item.team
    }

    private fun bindEmployeeViewHolder(holder: EmployeeViewHolder, position: Int) {
        val item = getItem(position) as EmployeeGroupedItem.Employee
        holder.binding.apply {
            listItemTitleText.text = item.full_name
            listItemSubtitleText.text = item.team
            listItemIconLeft.load(item.photo_url_small) {
                placeholder(R.drawable.block_white)
            }
        }
    }

    inner class HeaderViewHolder(
        private val rootView: View,
        val binding: ItemViewHeaderBinding = ItemViewHeaderBinding.bind(rootView)
    ) : RecyclerView.ViewHolder(rootView)

    inner class EmployeeViewHolder(
        private val rootView: View,
        val binding: ItemViewListBinding = ItemViewListBinding.bind(rootView)
    ) : RecyclerView.ViewHolder(rootView)

    companion object {
        private const val VIEW_TYPE_HEADER = R.layout.item_view_header
        private const val VIEW_TYPE_EMPLOYEE = R.layout.item_view_list

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<EmployeeGroupedItem>() {
            override fun areItemsTheSame(
                oldItem: EmployeeGroupedItem,
                newItem: EmployeeGroupedItem
            ): Boolean {
                val areHeaderItemTheSame =
                    oldItem is EmployeeGroupedItem.Header && newItem is EmployeeGroupedItem.Header
                val areEmployeeItemTheSame =
                    oldItem is EmployeeGroupedItem.Employee && newItem is EmployeeGroupedItem.Employee
                return areHeaderItemTheSame || areEmployeeItemTheSame
            }

            override fun areContentsTheSame(
                oldItem: EmployeeGroupedItem,
                newItem: EmployeeGroupedItem
            ): Boolean {
                return when {
                    oldItem is EmployeeGroupedItem.Header && newItem is EmployeeGroupedItem.Header -> oldItem == newItem
                    oldItem is EmployeeGroupedItem.Employee && newItem is EmployeeGroupedItem.Employee -> oldItem == newItem
                    else -> false
                }
            }
        }
    }
}