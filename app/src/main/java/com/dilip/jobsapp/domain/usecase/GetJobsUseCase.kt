package com.dilip.jobsapp.domain.usecase

import com.dilip.jobsapp.data.response.JobsResponse
import com.dilip.jobsapp.domain.repository.JobsRepository
import javax.inject.Inject

class GetJobsUseCase @Inject constructor(
    private val jobsRepository: JobsRepository
) {
    suspend operator fun invoke(
        title: String,
        location: Int,
        salary: String,
        phone: String
    ) : JobsResponse {
        val response = jobsRepository.getJobs(title, location, salary, phone)
        if (response.body() == null) {
            if (response.code() == 404)
                throw Exception("No jobs found")
            else if (response.code() == 500)
                throw Exception("Server error")
            else if (response.code() == 401)
                throw Exception("Unauthorized")
            else if (response.code() == 400)
                throw Exception("Bad request")
            else
                throw Exception("No jobs found")
        }
       return jobsRepository.getJobs(title, location, salary, phone).body()!!
    }
}