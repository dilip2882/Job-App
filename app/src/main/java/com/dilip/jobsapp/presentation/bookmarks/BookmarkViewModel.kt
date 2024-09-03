package com.dilip.jobsapp.presentation.bookmarks

import androidx.lifecycle.ViewModel
import com.dilip.jobsapp.database.JobsDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(database: JobsDatabase) : ViewModel() {
    private val jobsDao = database.jobsDao()
    fun getBookmarks() = jobsDao.getJobs()
}