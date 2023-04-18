package com.example.clothingsuggestor.domain.entities

data class Cloth(
	val id: Int,
	val imageSource: Int,
	val minPreferredTemp: Double,
	val maxPreferredTemp: Double
)
