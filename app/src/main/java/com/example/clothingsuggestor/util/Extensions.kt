package com.example.clothingsuggestor.util

import com.example.clothingsuggestor.data.domain.WeatherData
import com.example.clothingsuggestor.data.domain.Interval
import com.example.clothingsuggestor.data.domain.TimeLine
import com.example.clothingsuggestor.data.domain.WeatherValues
import org.json.JSONObject

fun JSONObject.toWeatherData(): WeatherData {
	val data = this.getJSONObject(Constants.RESPONSE_DATA)
	val timeLinesArray = data.getJSONArray(Constants.RESPONSE_TIMELINES)

	val listOfTimeLines = mutableListOf<TimeLine>()

	for (i in 0 until timeLinesArray.length()) {
		val timeLineObject = timeLinesArray.getJSONObject(i)

		val timeStep = timeLineObject.getString(Constants.RESPONSE_TIME_STEP)
		val endTime = timeLineObject.getString(Constants.RESPONSE_END_TIME)
		val startTime = timeLineObject.getString(Constants.RESPONSE_START_TIME)

		val intervalsArray = timeLineObject.getJSONArray(Constants.RESPONSE_INTERVALS)

		val listOfIntervals = mutableListOf<Interval>()

		for (j in 0 until intervalsArray.length()) {
			val intervalJsonObject = intervalsArray.getJSONObject(j)

			val intervalStartTime = intervalJsonObject.getString(Constants.RESPONSE_START_TIME)
			val values = intervalJsonObject.getJSONObject(Constants.RESPONSE_VALUES)
			val temperature = values.getString(Constants.RESPONSE_TEMPERATURE).toDouble()
			val humidity = values.getString(Constants.RESPONSE_HUMIDITY).toDouble()
			val windSpeed = values.getString(Constants.RESPONSE_WIND_SPEED).toDouble()
			val cloudCover = values.getString(Constants.RESPONSE_CLOUD_COVER).toDouble()
			val weatherCode = values.getString(Constants.RESPONSE_WEATHER_CODE).toInt()

			listOfIntervals.add(
				Interval(
					intervalStartTime,
					WeatherValues(temperature, humidity, windSpeed, cloudCover, weatherCode)
				)
			)
		}

		listOfTimeLines.add(TimeLine(timeStep, endTime, startTime, listOfIntervals))
	}
	return WeatherData(listOfTimeLines)
}

fun String.dateOnly(): String {
	return this.substring(0, 10).trim()
}
