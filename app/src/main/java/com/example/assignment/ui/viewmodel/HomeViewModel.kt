package com.example.assignment.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment.data.InsuranceProductDetailsItem
import com.example.assignment.data.InsuranceProductItem

import com.example.assignment.network.RetrofitInstance
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type


import com.example.assignment.Utility.UserEventLogger
import com.example.assignment.Utility.UserInteractionEvent



class HomeViewModel : ViewModel() {

    fun onScreenOpened(query: String) {
        logEvent(UserInteractionEvent.ScreenViewed(query))
    }

    fun onSearch(query: String) {
        logEvent(UserInteractionEvent.SearchPerformed(query))
    }

    fun onItemClicked(itemId: String) {
        logEvent(UserInteractionEvent.ItemSelected(itemId, "product"))
    }

    private fun logEvent(event: UserInteractionEvent) {
        viewModelScope.launch {
            UserEventLogger.log(event)
        }
    }

    private val _agentsData : MutableStateFlow<List<InsuranceProductItem>> = MutableStateFlow(listOf())
    val agentsData : StateFlow<List<InsuranceProductItem>> = _agentsData
    var selectedAgents by mutableStateOf<InsuranceProductItem?>(value = null)
        private set

    private val _agentsDetailData : MutableStateFlow<List<InsuranceProductDetailsItem>> = MutableStateFlow(listOf())
    val agentsDetailData : StateFlow<List<InsuranceProductDetailsItem>> = _agentsDetailData



    var selectedWeapon by mutableStateOf<InsuranceProductDetailsItem?>(value = null)
        private set
    var tabIndex by mutableStateOf(0)
        private set

    var errorString : String = " "
    var loading: Boolean by mutableStateOf(false)

    fun tabIndex(index: Int){
        viewModelScope.launch {
            tabIndex = index
        }
    }
    init{
        retrieveInsuranceProductData()

    }
    fun selectedAgents(agents: InsuranceProductItem){

        retrieveInsuranceProductDetailsData(agents.id)
    }



    private fun retrieveInsuranceProductData(){
        viewModelScope.launch {
            loading = true
            val call : Call<JsonArray> = RetrofitInstance.apiService.getInsuranceProduct()
            call.enqueue(object : Callback<JsonArray> {
                override fun onResponse(
                    call: Call<JsonArray>,
                    response: Response<JsonArray>
                ) {
                    if(response.isSuccessful){
                        val gson = Gson()
                    //    val array: Response<JsonArray> = response
                        val type: Type = object : TypeToken<List<InsuranceProductItem?>?>() {}.type
                        val lista: List<InsuranceProductItem> = gson.fromJson(response.body(), type)
                        /// val responseData: List<InsuranceProductItem>? = response.body()?.insuranceProduct
                        val responseData: List<InsuranceProductItem>? = lista
                        if(responseData != null){
                            _agentsData.value = responseData.filter { dataItem ->  dataItem.title != null }

                        }
                        loading = false
                    } else{
                        loading = false
                    }
                }

                override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                    loading = false
                    Log.d("Failed Retrieve", "Network Error")
                }

            })
        }
    }

    private fun retrieveInsuranceProductDetailsData(id: String?) {
        viewModelScope.launch {
            loading = true
            val call : Call<JsonArray> = RetrofitInstance.apiService.getInsuranceProductDetails(id)
            call.enqueue(object : Callback<JsonArray> {
                override fun onResponse(
                    call: Call<JsonArray>,
                    response: Response<JsonArray>
                ) {
                    if(response.isSuccessful){
                        val gson = Gson()
                        val type: Type = object : TypeToken<List<InsuranceProductDetailsItem?>?>() {}.type
                        val list: List<InsuranceProductDetailsItem> = gson.fromJson(response.body(), type)
                        val responseData: List<InsuranceProductDetailsItem>? = list
                        if(responseData != null){
                            _agentsDetailData.value = responseData.filter { dataItem ->  dataItem.title != null }
                            selectedWeapon =  agentsDetailData.value.get(0)
                        }
                        loading = false
                    } else{
                        loading = false
                    }
                }

                override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                    loading = false
                    Log.d("Failed Retrieve", "Network Error")
                }

            })
        }
    }


}