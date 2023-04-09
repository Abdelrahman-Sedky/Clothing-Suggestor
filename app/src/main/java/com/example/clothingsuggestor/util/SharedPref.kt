package com.example.clothingsuggestor.util

import android.content.Context
import android.content.SharedPreferences

object SharedPref {
	private lateinit var sharedPreferences: SharedPreferences

	fun initSharedPref(context: Context) {
		sharedPreferences =
			context.getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
	}

	var toppingId: Int
		get() = sharedPreferences.getInt(Constants.KEY_TOPPING, 0)
		set(value) = sharedPreferences.edit().putInt(Constants.KEY_TOPPING, value).apply()

	var trouserId: Int
		get() = sharedPreferences.getInt(Constants.KEY_TROUSER, 0)
		set(value) = sharedPreferences.edit().putInt(Constants.KEY_TROUSER, value).apply()

	var shoeId: Int
		get() = sharedPreferences.getInt(Constants.KEY_SHOE, 0)
		set(value) = sharedPreferences.edit().putInt(Constants.KEY_SHOE, value).apply()

	var date: String?
		get() = sharedPreferences.getString(Constants.KEY_DATE, "")
		set(value) = sharedPreferences.edit().putString(Constants.KEY_DATE, value!!).apply()

}