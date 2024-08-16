package com.example.contacts.data

data class ModelContact(
   val contact : List<Contact>
)
data class Contact(
    val name: String,
    val phone: String,
    val description: String,
)
