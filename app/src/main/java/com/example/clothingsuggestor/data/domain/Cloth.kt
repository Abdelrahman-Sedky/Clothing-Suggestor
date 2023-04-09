package com.example.clothingsuggestor.data.domain

data class Cloth(
	val id: Int,
	val imageSource: Int,
	val minPreferredTemp: Double,
	val maxPreferredTemp: Double
)
