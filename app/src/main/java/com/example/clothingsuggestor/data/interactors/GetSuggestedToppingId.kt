package com.example.clothingsuggestor.data.interactors

import com.example.clothingsuggestor.data.DataSource
import com.example.clothingsuggestor.data.domain.Cloth

class GetSuggestedToppingId(private val dataSource: DataSource) {

	fun execute(
		temperature: Double,
		currentDate: String,
		savedDate: String,
		savedToppingId: Int
	): Int {

		return if (currentDate == savedDate && savedToppingId != 0) {
			savedToppingId
		} else {
			val filteredTopping =
				dataSource.toppings.filter {
					isTemperatureInRange(temperature, it) &&
							isIdNotSaved(savedToppingId, it)
				}.takeIf { it.isNotEmpty() }

			filteredTopping?.random()?.id ?: savedToppingId
		}
	}

	private fun isTemperatureInRange(temperature: Double, cloth: Cloth): Boolean {
		return temperature >= cloth.minPreferredTemp
				&& temperature < cloth.maxPreferredTemp
	}

	private fun isIdNotSaved(savedToppingId: Int, cloth: Cloth): Boolean {
		return savedToppingId != cloth.id
	}

}