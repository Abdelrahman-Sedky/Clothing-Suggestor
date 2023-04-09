package com.example.clothingsuggestor.data.domain

class WeatherData(
	private val timeLines: List<TimeLine>,
) {
	fun getStartTime(): String = timeLines[0].startTime
	fun getTemperature(): Double = timeLines[0].intervalsList[0].values.temperature
	fun getHumidity(): Double = timeLines[0].intervalsList[0].values.humidity
	fun getWindSpeed(): Double = timeLines[0].intervalsList[0].values.windSpeed
	fun getCloudCover(): Double = timeLines[0].intervalsList[0].values.cloudCover
	fun getWeatherCode(): Int = timeLines[0].intervalsList[0].values.weatherCode
}
