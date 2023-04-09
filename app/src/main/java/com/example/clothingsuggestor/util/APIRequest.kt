package com.example.clothingsuggestor.util

import okhttp3.*
import java.io.IOException

object APIRequest {

	fun currentWeatherStateRequest(
		onResponse: (response: Response) -> Unit,
		onFailure: () -> Unit,
	) {
		val client = OkHttpClient()
		val url = HttpUrl.Builder()
			.scheme("https")
			.host("api.tomorrow.io")
			.addPathSegment("v4")
			.addPathSegment("timelines")
			.addQueryParameter("location", "30.0444,31.2357")
			.addQueryParameter("fields", "temperature,humidity,windSpeed,cloudCover,weatherCode")
			.addQueryParameter("timesteps", "current")
			.addQueryParameter("units", "metric")
			.addQueryParameter("apikey", Constants.API_KEY)
			.build()

		val request = Request.Builder().url(url).build()

		client.newCall(request).enqueue(object : Callback {
			override fun onFailure(call: Call, e: IOException) = onFailure()
			override fun onResponse(call: Call, response: Response) = onResponse(response)
		})
	}

}