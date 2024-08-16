package com.example.contacts.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.data.ModelContact
import com.example.contacts.databinding.ItemContactBinding

class AdapterContacts : RecyclerView.Adapter<AdapterContacts.ContactHolder>() {
    var modelContactData: MutableList<ModelContact>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactHolder(binding)
    }

    override fun getItemCount() = modelContactData?.size ?: 0

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        val contact = modelContactData?.get(position)
        if (contact != null) {
            holder.bind(contact)
        }
    }

    inner class ContactHolder(private val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: ModelContact) {
            binding.apply {
                tvName.text = contact.name
                tvPhone.text = contact.phone
            }
        }
    }

    fun addItem() {
        notifyItemInserted(modelContactData?.size?: 0)
    }
}