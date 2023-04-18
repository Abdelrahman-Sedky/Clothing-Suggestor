package com.example.clothingsuggestor.domain.interactors

import com.example.clothingsuggestor.data.local.DataSource
import com.example.clothingsuggestor.domain.entities.Cloth

class GetSuggestedTrouserId(private val dataSource: DataSource) {

	fun execute(
		temperature: Double,
		currentDate: String,
		savedDate: String,
		savedTrouserId: Int
	): Int {
		return if (currentDate == savedDate && savedTrouserId != 0) {
			savedTrouserId
		} else {
			val filteredTrousers =
				dataSource.trousers.filter {
					isTemperatureInRange(temperature, it) &&
							isIdNotSaved(savedTrouserId, it)
				}.takeIf { it.isNotEmpty() }

			filteredTrousers?.random()?.id ?: savedTrouserId
		}
	}

	private fun isTemperatureInRange(temperature: Double, cloth: Cloth): Boolean {
		return temperature >= cloth.minPreferredTemp
				&& temperature < cloth.maxPreferredTemp
	}

	private fun isIdNotSaved(savedTrouserId: Int, cloth: Cloth): Boolean {
		return savedTrouserId != cloth.id
	}
}