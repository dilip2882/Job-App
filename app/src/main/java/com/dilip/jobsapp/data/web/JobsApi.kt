package com.dilip.jobsapp.data.web

import com.dilip.jobsapp.data.response.JobsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface JobsApi {
    @GET("common/jobs?page=1")
    suspend fun getJobs(
        @Query("title") title: String?,
        @Query("city_location") location: Int,
        @Query("salary") salary: String?,
        @Query("phone") phone: String? = "123456789",
//        @Query("api-key") apiKey: String = API_KEY,
    ): Response<JobsResponse>
}

