package com.example.clothingsuggestor.data.domain

data class WeatherValues(
	val temperature: Double,
	val humidity: Double,
	val windSpeed: Double,
	val cloudCover: Double,
	val weatherCode: Int,
)