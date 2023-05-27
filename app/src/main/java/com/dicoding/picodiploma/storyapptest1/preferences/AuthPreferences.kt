package com.dicoding.picodiploma.storyapptest1.preferences

import android.content.Context
import com.dicoding.picodiploma.storyapptest1.network.model.User

class AuthPreferences(context: Context) {
    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    fun setUser(value: User) {
        val editor = preferences.edit()
        editor.putString(NAME, value.name)
        editor.putString(EMAIL, value.email)
        editor.putString(ID, value.userId)
        editor.putString(TOKEN, value.token)
        editor.apply()
    }

    fun getUser(): User =
        User(
            userId = preferences.getString(ID, "").toString(),
            name = preferences.getString(NAME, "").toString(),
            email = preferences.getString(EMAIL, "").toString(),
            token = preferences.getString(TOKEN, "").toString()
        )

    fun setStatusLogin(value: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(LOGIN, value)
        editor.apply()
    }

    fun getStatusLogin(): Boolean {
        return preferences.getBoolean(LOGIN, false)
    }

    fun getToken(): String? {
        return preferences.getString(TOKEN, "")
    }

    fun logout() {
        val editor = preferences.edit()
        editor.clear().apply()
    }


    companion object {
        private const val PREFS_NAME = "prefs_name"
        private const val ID = "id"
        private const val NAME = "name"
        private const val EMAIL = "emial"
        private const val TOKEN = "token"
        private const val LOGIN = "login"
    }
}
