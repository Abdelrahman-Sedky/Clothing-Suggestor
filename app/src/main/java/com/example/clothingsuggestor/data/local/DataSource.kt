package com.example.clothingsuggestor.data.local

import com.example.clothingsuggestor.R
import com.example.clothingsuggestor.domain.entities.Cloth

class DataSource {

	val toppings: List<Cloth> = listOf(
		Cloth(1, R.drawable.topping1, 20.0, 25.0),
		Cloth(2, R.drawable.topping2, 15.0, 20.0),
		Cloth(3, R.drawable.topping3, 10.0, 15.0),
		Cloth(4, R.drawable.topping4, -90.0, 10.0),
		Cloth(5, R.drawable.topping5, 25.0, 30.0),
		Cloth(6, R.drawable.topping6, 30.0, 60.0),
	)

	val trousers: List<Cloth> = listOf(
		Cloth(1, R.drawable.trouser1, 25.0, 30.0),
		Cloth(2, R.drawable.trouser2, -90.0, 60.0),
		Cloth(3, R.drawable.trouser3, -90.0, 60.0),
		Cloth(4, R.drawable.trouser4, -90.0, 60.0),
		Cloth(5, R.drawable.trouser5, -90.0, 60.0),
		Cloth(6, R.drawable.trouser6, 30.0, 60.0),
	)

	val shoes: List<Cloth> = listOf(
		Cloth(1, R.drawable.shoe1, 25.0, 35.0),
		Cloth(2, R.drawable.shoe2, 25.0, 30.0),
		Cloth(3, R.drawable.shoe3, 35.0, 60.0),
		Cloth(4, R.drawable.shoe4, -90.0, 25.0),
		Cloth(5, R.drawable.shoe5, 25.0, 60.0),
		Cloth(6, R.drawable.shoe6, -90.0, 20.0),
	)
}