package com.example.clothingsuggestor.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding>(private val bindingInflater: (LayoutInflater) -> VB) :
	AppCompatActivity() {

	protected lateinit var binding: VB

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = bindingInflater(layoutInflater)
		setContentView(binding.root)
		setup()
	}

	abstract fun setup()
}