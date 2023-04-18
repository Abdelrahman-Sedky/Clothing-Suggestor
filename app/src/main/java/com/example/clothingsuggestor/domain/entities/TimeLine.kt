package com.example.clothingsuggestor.domain.entities

data class TimeLine(
	val timeStep: String,
	val endTime: String,
	val startTime: String,
	val intervalsList: List<Interval>,
)
