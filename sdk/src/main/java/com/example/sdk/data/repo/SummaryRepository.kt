package com.example.sdk.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sdk.data.api.SummaryApi
import com.example.sdk.data.model.SummaryItemResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SummaryRepository(private val api: SummaryApi) {
    fun decode(code: String): LiveData<SummaryItemResponse?> {
        val liveData = MutableLiveData<SummaryItemResponse?>()

        api.decode().enqueue(object : Callback<SummaryItemResponse?> {
            override fun onResponse(
                call: Call<SummaryItemResponse?>,
                response: Response<SummaryItemResponse?>
            ) {
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<SummaryItemResponse?>, t: Throwable) {
                liveData.postValue(null)
            }

        })

        return liveData
    }

    fun makePayment(): Boolean = true
}