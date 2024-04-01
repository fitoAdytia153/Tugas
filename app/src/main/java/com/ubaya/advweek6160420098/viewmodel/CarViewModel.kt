package com.ubaya.advweek6160420098.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.advweek6160420098.model.Car

class CarViewModel(application: Application): AndroidViewModel(application)  {
    val carsLD = MutableLiveData<ArrayList<Car>>()
    val carLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh() {

        carLoadErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue( getApplication()  )
        val url = "http://10.0.2.2/cars.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                try {
                    val gson = Gson()
                    val carType = object : TypeToken<List<Car>>() {}.type // Use this if expecting a list
                    val cars = gson.fromJson<List<Car>>(response, carType) // Adjust according to expected response
                    carsLD.value = ArrayList(cars) // Make sure this matches the expected data type
                    loadingLD.value = false
                } catch (e: Exception) {
                    Log.e(TAG, "Error parsing JSON: ", e)
                    carLoadErrorLD.value = true
                    loadingLD.value = false
                }
            }
            ,
            { error ->
                Log.e(TAG, "Vollery error: $error")
                carLoadErrorLD.value = true
                loadingLD.value = false
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}