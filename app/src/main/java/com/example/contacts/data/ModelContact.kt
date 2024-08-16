package com.example.contacts.data

import java.util.UUID

data class ModelContact(
    val name: String,
    val phone: String,
    val description: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ModelContact) return false

        return name == other.name && phone == other.phone
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + phone.hashCode()
        return result
    }
}
