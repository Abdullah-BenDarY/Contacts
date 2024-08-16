package com.example.contacts.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.contacts.adapters.AdapterContacts
import com.example.contacts.base.BaseFragment
import com.example.contacts.data.ModelContact
import com.example.contacts.databinding.FragmentHomeBinding
import com.example.contacts.util.EXIST
import com.example.contacts.util.INVALID
import com.example.contacts.util.REQUIRED
import com.example.contacts.util.SharedPrefs
import com.example.contacts.util.showToast
import java.util.UUID

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val adapterContacts = AdapterContacts()
    private val modelContactList = mutableListOf<ModelContact>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClicks()
        setupAdapter(modelContactList)
    }

    private fun setupAdapter(contactList: MutableList<ModelContact>) {
        adapterContacts.modelContactData = contactList
        binding.rvContacts.adapter = adapterContacts
    }

    override fun onClicks() {

        super.onClicks()
        adapterContacts.setOnNavigateClick {
            setSahredPrefsData(it.name , it.phone , it.description)
            findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment())
        }
        binding.apply {
            btnSave.setOnClickListener {
                val name = editName.text.toString()
                val phone = editPhone.text.toString()
                val description = editDescription.text.toString()
                if (validate(name, phone)) {
                    saveContact(name, phone, description)
                }
            }
            adapterContacts.setOnRootClick {
                dialPhoneNumber(it)
            }
        }
    }

    private fun validate(name: String, phone: String): Boolean {
        return when {
            name.isEmpty() -> {
                binding.editName.error = REQUIRED
                binding.editName.requestFocus()
                false
            }

            phone.isEmpty() -> {
                binding.editPhone.error = REQUIRED
                binding.editPhone.requestFocus()
                false
            }
            phone.length != 11 -> {
                binding.editPhone.error = INVALID
                binding.editPhone.requestFocus()
                false
            }
            else -> true
        }
    }

    private fun saveContact(name: String, phone: String, description: String) {
        val modelContact = ModelContact(name, phone, description)
        if (modelContactList.contains(modelContact)) {
            showToast(EXIST)
        } else {
            modelContactList.add(modelContact)
            adapterContacts.addItem()
        }
    }

    private fun setSahredPrefsData(name: String , phone: String , description: String){
        SharedPrefs.setContactName(name)
        SharedPrefs.setContactphone(phone)
        SharedPrefs.setContactDescription(description)

    }
    @SuppressLint("QueryPermissionsNeeded")
    private fun dialPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        }
    }
}