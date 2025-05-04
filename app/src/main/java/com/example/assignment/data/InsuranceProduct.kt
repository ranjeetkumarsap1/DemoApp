package com.example.assignment.data

import com.google.gson.annotations.SerializedName

data class InsuranceProduct(

	@field:SerializedName("InsuranceProduct")
	val insuranceProduct: List<InsuranceProductItem>? = null
)

data class InsuranceProductItem(

	@field:SerializedName("covrage")
	val covrage: String? = null,

	@field:SerializedName("monthly_premium")
	val monthlyPremium: Int? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("tenure")
	val tenure: String? = null
)
