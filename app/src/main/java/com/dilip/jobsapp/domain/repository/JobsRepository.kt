package com.dilip.jobsapp.domain.repository

import com.dilip.jobsapp.data.response.JobsResponse
import retrofit2.Response

interface JobsRepository {

    suspend fun getJobs(
        title: String,
        location: Int,
        salary: String,
        phone: String
    ) : Response<JobsResponse>
}