package com.dilip.jobsapp.utils

import com.dilip.jobsapp.data.model.Result
import com.google.gson.Gson
import java.net.URLDecoder
import java.net.URLEncoder

object NavRoute {
    fun createJobsDetailsRoute(news: Result, isLocal: Boolean? = false): String {
        val encodedUrl = URLEncoder.encode(news.custom_link, "utf-8")
        val tempNews = news.copy(custom_link = encodedUrl)
        val gson = Gson().toJson(tempNews)
        return "/details/news=$gson&isLocal=$isLocal"
    }

    fun getJobsFromRoute(json: String): Result {
        val news = Gson().fromJson(json, Result::class.java)
        val decodeUrl = URLDecoder.decode(news.id.toString(), "utf-8")
        return news.copy(custom_link = decodeUrl)
    }
}