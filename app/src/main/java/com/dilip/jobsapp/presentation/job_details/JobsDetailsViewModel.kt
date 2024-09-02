package com.dilip.jobsapp.presentation.job_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dilip.jobsapp.State
import com.dilip.jobsapp.data.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobsDetailsViewModel @Inject constructor(
//    database: JobsDatabase
) : ViewModel() {
    private val _state = MutableStateFlow<State<BookmarkAction>>(State.Loading)
    val state = _state as StateFlow<State<BookmarkAction>>

//    private val jobsDao = database.jobsDao()
    fun addJobs(jobs: Result) {
        viewModelScope.launch {
            try {
                _state.tryEmit(State.Loading)
//                jobsDao.addJobs(jobs)
                _state.tryEmit(State.Success(BookmarkAction.ADD))
            } catch (e: Exception) {
                _state.tryEmit(State.Error(e.message.toString()))
            }
        }
    }

    fun removeNews(jobs: Result) {
        viewModelScope.launch {
            try {
                _state.tryEmit(State.Loading)
//                jobsDao.deleteJobs(jobs)
                _state.tryEmit(State.Success(BookmarkAction.REMOVE))
            } catch (e: Exception) {
                _state.tryEmit(State.Error(e.message.toString()))
            }
        }
    }
}

enum class BookmarkAction {
    ADD, REMOVE
}