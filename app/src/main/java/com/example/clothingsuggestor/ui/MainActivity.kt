package com.example.clothingsuggestor.ui

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.clothingsuggestor.data.local.DataSource
import com.example.clothingsuggestor.data.local.WeatherCodes
import com.example.clothingsuggestor.domain.interactors.GetSuggestedShoeId
import com.example.clothingsuggestor.domain.interactors.GetSuggestedToppingId
import com.example.clothingsuggestor.domain.interactors.GetSuggestedTrouserId
import com.example.clothingsuggestor.databinding.ActivityMainBinding
import com.example.clothingsuggestor.ui.adapters.ClothAdapter
import com.example.clothingsuggestor.data.remote.APIRequest
import com.example.clothingsuggestor.data.local.SharedPref
import com.example.clothingsuggestor.data.remote.Mapper
import okhttp3.Response
import org.json.JSONObject
import kotlin.math.roundToInt


class MainActivity : BaseActivity<ActivityMainBinding>() {

	private val dataSource by lazy { DataSource() }
	private val getSuggestedToppingId by lazy { GetSuggestedToppingId(dataSource) }
	private val getSuggestedTrouserId by lazy { GetSuggestedTrouserId(dataSource) }
	private val getSuggestedShoeId by lazy { GetSuggestedShoeId(dataSource) }


	override val bindingInflater: (LayoutInflater) -> ActivityMainBinding =
		ActivityMainBinding::inflate

	override fun setup() {
		APIRequest.currentWeatherStateRequest(::onResponse, ::onFailure)
		initRecyclerViews()
		SharedPref.initSharedPref(this)
	}

	private fun initRecyclerViews() {
		val toppingAdapter = ClothAdapter(dataSource.toppings)
		val trouserAdapter = ClothAdapter(dataSource.trousers)
		val shoeAdapter = ClothAdapter(dataSource.shoes)

		binding.topClothRecycler.let {
			it.adapter = toppingAdapter
			attachSnapBehaviour(it)
		}

		binding.trouserRecycler.let {
			it.adapter = trouserAdapter
			attachSnapBehaviour(it)
		}

		binding.shoeRecycler.let {
			it.adapter = shoeAdapter
			attachSnapBehaviour(it)
		}
	}

	private fun attachSnapBehaviour(targetRecycler: RecyclerView) {
		val helper = LinearSnapHelper()
		helper.attachToRecyclerView(targetRecycler)
	}


	private fun onResponse(response: Response) {
		response.body?.string()?.let { jsonString ->
			val jsonObject = JSONObject(jsonString)
			val result = Mapper.convertToWeatherData(jsonObject)

			val currentDate = result.getStartTime().substring(0, 10).trim()
			val temperature = result.getTemperature()
			val humidity = result.getHumidity()
			val windSpeed = result.getWindSpeed()
			val cloudCover = result.getCloudCover()
			val weatherCode = result.getWeatherCode()

			runOnUiThread {
				updateUIData(temperature, humidity, windSpeed, cloudCover, weatherCode)
				SharedPref.date = currentDate
				updateRecyclersWithSuggestions(temperature, currentDate)
			}
		}
	}

	private fun onFailure() {
		if (!isNetworkAvailable()) {
			binding.noInternet.visibility = View.VISIBLE
		}
	}

	private fun updateUIData(
		temperature: Double,
		humidity: Double,
		windSpeed: Double,
		cloudCover: Double,
		weatherCode: Int
	) {
		binding.temperatureValue.text = temperature.roundToInt().toString().plus(" °")
		binding.humidityValue.text = humidity.toString().plus(" %")
		binding.windValue.text = windSpeed.toString().plus(" M/S")
		binding.cloudCoverValue.text = cloudCover.toString().plus(" %")
		binding.weatherState.text = WeatherCodes.weatherCode[weatherCode]

	}

	private fun updateRecyclersWithSuggestions(temperature: Double, currentDate: String) {
		val suggestedToppingId =
			getSuggestedToppingId.execute(
				temperature,
				currentDate,
				SharedPref.date!!,
				SharedPref.toppingId
			)

		val suggestedTrouserId =
			getSuggestedTrouserId.execute(
				temperature,
				currentDate,
				SharedPref.date!!,
				SharedPref.trouserId
			)

		val suggestedShoeId =
			getSuggestedShoeId.execute(
				temperature,
				currentDate,
				SharedPref.date!!,
				SharedPref.shoeId
			)

		binding.topClothRecycler.smoothScrollToPosition(suggestedToppingId - 1)
		binding.trouserRecycler.smoothScrollToPosition(suggestedTrouserId - 1)
		binding.shoeRecycler.smoothScrollToPosition(suggestedShoeId - 1)
	}

	override fun onPause() {
		super.onPause()
		if (isNetworkAvailable()) {
			saveData()
		}
	}

	private fun isNetworkAvailable(): Boolean {
		val connectivityManager = getSystemService(ConnectivityManager::class.java)
		val currentNetwork = connectivityManager.activeNetwork
		val capabilities = connectivityManager.getNetworkCapabilities(currentNetwork)
		return capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
	}

	private fun saveData() {
		val toppingPosition =
			getClothPosition(binding.topClothRecycler.layoutManager)

		val trouserPosition =
			getClothPosition(binding.trouserRecycler.layoutManager)

		val shoePosition =
			getClothPosition(binding.shoeRecycler.layoutManager)

		SharedPref.toppingId = dataSource.toppings[toppingPosition].id
		SharedPref.trouserId = dataSource.trousers[trouserPosition].id
		SharedPref.shoeId = dataSource.shoes[shoePosition].id
	}

	private fun getClothPosition(linearLayoutManager: LayoutManager?): Int {
		return (linearLayoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
	}


}