package com.example.tadatest.prefs

import android.content.Context
import androidx.core.content.edit

class PrefManager(private val context: Context) {
    private val pref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

    fun clear() = pref.edit { clear() }

    companion object : SingletonHolder<PrefManager, Context>(::PrefManager) {
        private const val FILE_NAME = "AUTHENTICATION_FILE_NAME"
    }
    private val KEY_IS_LOGIN = "IS_LOGIN"
    private val KEY_USER_NAME = "USER_NAME"
    private val KEY_PASSWORD = "PASSWORD"

    var isLogin: Boolean
        get() = pref.getBoolean(KEY_IS_LOGIN, false)
        set(value) = pref.edit { putBoolean(KEY_IS_LOGIN, value) }

    var userName: String?
        get() = pref.getString(KEY_USER_NAME, null)
        set(value) = pref.edit { putString(KEY_USER_NAME, value) }

    var userPassword: String?
        get() = pref.getString(KEY_PASSWORD, null)
        set(value) = pref.edit { putString(KEY_PASSWORD, value) }
}