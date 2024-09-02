package com.dilip.jobsapp.data.repository

import com.dilip.jobsapp.data.response.JobsResponse
import com.dilip.jobsapp.data.web.JobsApi
import com.dilip.jobsapp.domain.repository.JobsRepository
import retrofit2.Response
import javax.inject.Inject

class JobsRepositoryImpl @Inject constructor(val api: JobsApi): JobsRepository {
    override suspend fun getJobs(
        title: String,
        location: Int,
        salary: String,
        phone: String
    ): Response<JobsResponse> {
        return api.getJobs(title, location, salary, phone)
    }
}