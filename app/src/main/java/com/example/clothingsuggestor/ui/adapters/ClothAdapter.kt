package com.example.clothingsuggestor.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clothingsuggestor.data.domain.Cloth
import com.example.clothingsuggestor.databinding.ItemImageBinding

class ClothAdapter(private val clothList: List<Cloth>) :
	RecyclerView.Adapter<ClothAdapter.ClothViewHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothViewHolder {
		val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return ClothViewHolder(binding)
	}

	override fun getItemCount(): Int = clothList.size

	override fun onBindViewHolder(holder: ClothViewHolder, position: Int) {
		holder.bind(clothList[position])
	}

	class ClothViewHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
		fun bind(cloth: Cloth) {
			binding.clothImage.setImageResource(cloth.imageSource)
		}
	}
}