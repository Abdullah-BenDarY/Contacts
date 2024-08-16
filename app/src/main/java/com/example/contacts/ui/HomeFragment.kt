package com.example.contacts.ui

import android.os.Bundle
import android.view.View
import com.example.contacts.base.BaseFragment
import com.example.contacts.data.Contact
import com.example.contacts.databinding.FragmentHomeBinding
import com.example.contacts.util.REQUIRED

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val contactList : MutableList<Contact>?= null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClicks()
    }

    override fun onClicks() {
        super.onClicks()
        binding.apply {
            btnSave.setOnClickListener {
                val name = editName.text.toString()
                val phone = editPhone.text.toString()
                val description = editDescription.text.toString()
                if (validate(name , phone)) {
                    saveContact(name , phone , description)}
            }

        }
    }

    private fun validate(name: String, phone: String): Boolean {
        if (name.isEmpty()) {
            binding.editName.error = REQUIRED
            binding.editName.requestFocus()
            return false
        } else if (phone.isEmpty()) {
            binding.editPhone.error = REQUIRED
            binding.editPhone.requestFocus()
            return false
        } else {
            return true
        }
    }

    private fun saveContact(name: String, phone: String, description: String) {
        val contact = Contact(name , phone , description)
        contactList?.add(contact)
    }
}

