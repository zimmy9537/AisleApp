package com.zimmy.sample.aisleapp.util

import android.content.Context
import android.content.SharedPreferences


open class AppPreference(mContext: Context) {

    private val AISLE_PREFERENCE = "aisle_preference"

    private var pref: SharedPreferences = mContext.getSharedPreferences(
        AISLE_PREFERENCE, Context.MODE_PRIVATE
    )

    companion object {
        val AUTH_KEY = "AUTH_KEY"
    }

    fun clearPreferences() {
        pref.edit().clear().apply()
    }


    fun saveString(key: String, value: String?) {
        pref.edit().putString(key, value).apply()
    }

    fun saveInteger(key: String, value: Int) {
        pref.edit().putInt(key, value).apply()
    }

    fun saveFloat(key: String, value: Float) {
        pref.edit().putFloat(key, value).apply()
    }

    fun getInteger(key: String, defaultValue: Int): Int {
        return pref.getInt(key, defaultValue)
    }

    fun getFloat(key: String, defaultValue: Float): Float {
        return pref.getFloat(key, defaultValue)
    }

    fun getString(key: String): String? {
        return pref.getString(key, "")
    }

    fun getString(key: String, defaultValue: String): String? {
        return pref.getString(key, defaultValue)
    }

    fun saveBoolean(key: String, value: Boolean) {
        pref.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String): Boolean {
        return pref.getBoolean(key, false)
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return pref.getBoolean(key, defaultValue)
    }

    fun clearPref() {
        pref.edit().clear().apply()
    }

    fun saveLong(key: String, value: Long) {
        pref.edit().putLong(key, value).apply()
    }

    fun getLong(key: String): Long {
        return pref.getLong(key, 0)
    }
}