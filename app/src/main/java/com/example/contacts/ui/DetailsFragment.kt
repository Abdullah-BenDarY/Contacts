package com.example.contacts.ui


import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.navigateUp
import com.example.contacts.base.BaseFragment
import com.example.contacts.data.ModelContact
import com.example.contacts.databinding.FragmentDetailsBinding
import com.example.contacts.util.SharedPrefs

class DetailsFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClicks()
        setUpUi()
    }

    override fun onClicks() {
        binding.apply {
            tvDetails.setOnClickListener {
                findNavController().navigateUp()
            }
            btnCall.setOnClickListener {
                dialPhoneNumber(tvContactPhone.text.toString())
            }
        }
    }
    private fun setUpUi(){
        binding.apply {
            tvContactName.text = SharedPrefs.getContactName()
            tvContactPhone.text = SharedPrefs.getContactPhone()
            tvContactDescription.text = SharedPrefs.getContactDescription()
        }
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