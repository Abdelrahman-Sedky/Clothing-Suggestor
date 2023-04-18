package com.example.clothingsuggestor.domain.interactors

import com.example.clothingsuggestor.data.local.DataSource
import com.example.clothingsuggestor.domain.entities.Cloth

class GetSuggestedShoeId(private val dataSource: DataSource) {

	fun execute(
		temperature: Double,
		currentDate: String,
		savedDate: String,
		savedShoeId: Int
	): Int {
		return if (currentDate == savedDate && savedShoeId != 0) {
			savedShoeId
		} else {
			val filteredShoes =
				dataSource.shoes.filter {
					isTemperatureInRange(temperature, it) &&
							isIdNotSaved(savedShoeId, it)
				}.takeIf { it.isNotEmpty() }

			filteredShoes?.random()?.id ?: savedShoeId
		}
	}

	private fun isTemperatureInRange(temperature: Double, cloth: Cloth): Boolean {
		return temperature >= cloth.minPreferredTemp
				&& temperature < cloth.maxPreferredTemp
	}

	private fun isIdNotSaved(savedShoeId: Int, cloth: Cloth): Boolean {
		return savedShoeId != cloth.id
	}
}