package com.example.contacts.util

import android.content.Context
import android.content.SharedPreferences


object SharedPrefs {
    private const val PREFS_NAME = "user_data"
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun setContactName(name: String) {
        with(sharedPreferences.edit()) {
            putString("CONTACT_NAME", name)
            commit()
        }
    }

    fun getContactName(): String? {
        return sharedPreferences.getString("CONTACT_NAME", "")
    }

    fun setContactphone(name: String) {
        with(sharedPreferences.edit()) {
            putString("CONTACT_PHONE", name)
            commit()
        }
    }

    fun getContactPhone(): String? {
        return sharedPreferences.getString("CONTACT_PHONE", "")
    }
    fun setContactDescription(name: String) {
        with(sharedPreferences.edit()) {
            putString("CONTACT_DESCRIPTION", name)
            commit()
        }
    }
    fun getContactDescription(): String? {
        return sharedPreferences.getString("CONTACT_DESCRIPTION", "")
    }


}
