package com.dilip.jobsapp.utils

import com.dilip.jobsapp.data.model.Result
import com.google.gson.Gson
import java.net.URLDecoder
import java.net.URLEncoder

object NavRoute {
    fun createJobsDetailsRoute(
        jobs: Result,
        isLocal: Boolean? = false
    ): String {
        val encodedUrl = URLEncoder.encode(jobs.custom_link, "utf-8")
        val tempJobs = jobs.copy(custom_link = encodedUrl)
        val gson = Gson().toJson(tempJobs)
        return "/details/jobs=$gson&isLocal=$isLocal"
    }

    fun getJobsFromRoute(json: String): Result {
        val jobs = Gson().fromJson(json, Result::class.java)
        val decodeUrl = URLDecoder.decode(jobs.custom_link, "utf-8")
        return jobs.copy(custom_link = decodeUrl)
    }
}