package com.dilip.jobsapp.utils

import androidx.compose.ui.graphics.painter.Painter
import coil.decode.ImageSource
import com.dilip.jobsapp.data.model.Result
import com.google.gson.Gson
import java.net.URLDecoder
import java.net.URLEncoder

object NavRoute {
    fun createJobsDetailsRoute(
        jobs: Result,
        isLocal: Boolean? = false
    ): String {
        val gson = Gson().toJson(jobs)
        val encodedUrl = URLEncoder.encode(gson, "utf-8")
        return "/details/jobs=$encodedUrl&isLocal=$isLocal"
    }

    fun getJobsFromRoute(json: String): Result {
        val decodedUrl = URLDecoder.decode(json, "utf-8")
        return Gson().fromJson(decodedUrl, Result::class.java)    }

}