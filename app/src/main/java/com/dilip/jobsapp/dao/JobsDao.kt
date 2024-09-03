package com.dilip.jobsapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.dilip.jobsapp.data.model.Result
import kotlinx.coroutines.flow.Flow

@Dao
interface JobsDao {

    @Query("SELECT * FROM jobs")
    fun getJobs(): Flow<List<Result>>

    @Insert
    suspend fun addJobs(result: Result)

    @Delete
    suspend fun deleteJobs(result: Result)


}