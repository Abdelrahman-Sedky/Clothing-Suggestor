package com.example.clothingsuggestor.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

	abstract val bindingInflater: (LayoutInflater) -> VB
	private var _binding: VB? = null
	protected val binding get() = _binding!!

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		_binding = bindingInflater(layoutInflater)
		setContentView(binding.root)
		setup()
	}

	abstract fun setup()
}