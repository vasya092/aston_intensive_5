package com.example.astonintensive5.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.astonintensive5.R
import com.example.astonintensive5.databinding.ContactsListItemBinding
import com.example.astonintensive5.model.Contact

class ContactsListAdapter(
    private val onItemClicked: (Contact) -> Unit,
) : ListAdapter<Contact, ContactsListAdapter.ContactsListViewHolder>(DiffCallback) {

    class ContactsListViewHolder(private var binding: ContactsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact) {
            with(binding) {
                contactFullName.text = root.context.getString(R.string.contact_item_full_name_template, contact.name, contact.surname)
                contactNumber.text = contact.number
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsListViewHolder {
        return ContactsListViewHolder(
            ContactsListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ContactsListViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(currentItem)
        }
        holder.bind(currentItem)
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Contact>() {
            override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
                return oldItem == newItem
            }

        }
    }
}