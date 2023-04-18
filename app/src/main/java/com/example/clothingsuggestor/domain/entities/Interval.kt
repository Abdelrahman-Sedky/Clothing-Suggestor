package com.example.clothingsuggestor.domain.entities

data class Interval(
	val startTime: String,
	val values: WeatherValues,
)