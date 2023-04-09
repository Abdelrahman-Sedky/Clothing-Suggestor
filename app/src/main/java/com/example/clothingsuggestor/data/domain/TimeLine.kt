package com.example.clothingsuggestor.data.domain

data class TimeLine(
	val timeStep: String,
	val endTime: String,
	val startTime: String,
	val intervalsList: List<Interval>,
)
