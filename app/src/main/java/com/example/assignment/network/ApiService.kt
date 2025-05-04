package com.example.assignment.network


import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("InsuranceProduct")
    fun getInsuranceProduct() : Call<JsonArray>

    @GET("InsuranceProduct")
    fun getInsuranceProductDetails(
       @Query("id")  id : String ?
    ) : Call<JsonArray>

}