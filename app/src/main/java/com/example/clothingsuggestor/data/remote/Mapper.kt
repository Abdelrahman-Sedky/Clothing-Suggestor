package com.example.clothingsuggestor.data.remote

import com.example.clothingsuggestor.domain.entities.WeatherData
import com.example.clothingsuggestor.domain.entities.Interval
import com.example.clothingsuggestor.domain.entities.TimeLine
import com.example.clothingsuggestor.domain.entities.WeatherValues
import org.json.JSONObject

object Mapper {
	private const val RESPONSE_DATA = "data"
	private const val RESPONSE_TIMELINES = "timelines"
	private const val RESPONSE_TIME_STEP = "timestep"
	private const val RESPONSE_END_TIME = "endTime"
	private const val RESPONSE_START_TIME = "startTime"
	private const val RESPONSE_INTERVALS = "intervals"
	private const val RESPONSE_VALUES = "values"
	private const val RESPONSE_TEMPERATURE = "temperature"
	private const val RESPONSE_HUMIDITY = "humidity"
	private const val RESPONSE_WIND_SPEED = "windSpeed"
	private const val RESPONSE_CLOUD_COVER = "cloudCover"
	private const val RESPONSE_WEATHER_CODE = "weatherCode"

	fun convertToWeatherData(json: JSONObject): WeatherData {
		val data = json.getJSONObject(RESPONSE_DATA)
		val timeLinesArray = data.getJSONArray(RESPONSE_TIMELINES)

		val listOfTimeLines = mutableListOf<TimeLine>()

		for (i in 0 until timeLinesArray.length()) {
			val timeLineObject = timeLinesArray.getJSONObject(i)

			val timeStep = timeLineObject.getString(RESPONSE_TIME_STEP)
			val endTime = timeLineObject.getString(RESPONSE_END_TIME)
			val startTime = timeLineObject.getString(RESPONSE_START_TIME)

			val intervalsArray = timeLineObject.getJSONArray(RESPONSE_INTERVALS)

			val listOfIntervals = mutableListOf<Interval>()

			for (j in 0 until intervalsArray.length()) {
				val intervalJsonObject = intervalsArray.getJSONObject(j)

				val intervalStartTime = intervalJsonObject.getString(RESPONSE_START_TIME)
				val values = intervalJsonObject.getJSONObject(RESPONSE_VALUES)
				val temperature = values.getString(RESPONSE_TEMPERATURE).toDouble()
				val humidity = values.getString(RESPONSE_HUMIDITY).toDouble()
				val windSpeed = values.getString(RESPONSE_WIND_SPEED).toDouble()
				val cloudCover = values.getString(RESPONSE_CLOUD_COVER).toDouble()
				val weatherCode = values.getString(RESPONSE_WEATHER_CODE).toInt()

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
}