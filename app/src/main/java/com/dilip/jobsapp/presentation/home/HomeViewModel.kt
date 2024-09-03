package com.dilip.jobsapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dilip.jobsapp.State
import com.dilip.jobsapp.data.response.JobsResponse
import com.dilip.jobsapp.domain.usecase.GetJobsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getJobsUseCase: GetJobsUseCase) : ViewModel() {

    private val _state = MutableStateFlow<State<JobsResponse>>(State.Loading)
    val state = _state as StateFlow<State<JobsResponse>>

    private var job: Job? = null

    init {
        getJobs()
    }

    fun getJobs(text: String? = null, country: String? = null) {
        job?.cancel()
        job = viewModelScope.launch {
            _state.tryEmit(State.Loading)
            try {
                val result = getJobsUseCase.invoke("Android Developer", 25, "5000000", "6666613113")
                _state.tryEmit(State.Success(result))
            } catch (e: Exception) {
                _state.tryEmit(State.Error(e.message.toString()))
            }
        }
    }

}