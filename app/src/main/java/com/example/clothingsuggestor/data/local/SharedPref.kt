package com.example.clothingsuggestor.data.local

import android.content.Context
import android.content.SharedPreferences

object SharedPref {

	private const val SHARED_PREFERENCE_NAME = "SavedCloth"
	private const val KEY_TOPPING = "toppingId"
	private const val KEY_TROUSER = "trouserId"
	private const val KEY_SHOE = "shoeId"
	private const val KEY_DATE = "date"

	private lateinit var sharedPreferences: SharedPreferences

	fun initSharedPref(context: Context) {
		sharedPreferences =
			context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
	}

	var toppingId: Int
		get() = sharedPreferences.getInt(KEY_TOPPING, 0)
		set(value) = sharedPreferences.edit().putInt(KEY_TOPPING, value).apply()

	var trouserId: Int
		get() = sharedPreferences.getInt(KEY_TROUSER, 0)
		set(value) = sharedPreferences.edit().putInt(KEY_TROUSER, value).apply()

	var shoeId: Int
		get() = sharedPreferences.getInt(KEY_SHOE, 0)
		set(value) = sharedPreferences.edit().putInt(KEY_SHOE, value).apply()

	var date: String?
		get() = sharedPreferences.getString(KEY_DATE, "")
		set(value) = sharedPreferences.edit().putString(KEY_DATE, value!!).apply()

}