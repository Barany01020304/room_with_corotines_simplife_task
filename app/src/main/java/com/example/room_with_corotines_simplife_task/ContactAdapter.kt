package com.example.room_with_corotines_simplife_task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.room_with_corotines_simplife_task.databinding.ActivityAddContactBinding
import com.example.room_with_corotines_simplife_task.databinding.ListItemBinding

class ContactAdapter(private var contactList: List<ContactDataModel>,var onViewClick: OnViewClick) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        var binding =
            ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding,onViewClick)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        var currentItem = contactList[position]
        holder.binding.nameText.text = currentItem.name
        holder.binding.phoneText.text = currentItem.phone
    }

    class ContactViewHolder(var binding: ListItemBinding, var onViewClick: OnViewClick) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onViewClick.onViewClick(adapterPosition)
            }
        }
    }

    interface OnViewClick {
        fun onViewClick(position: Int)
    }
    companion object{
    }
}