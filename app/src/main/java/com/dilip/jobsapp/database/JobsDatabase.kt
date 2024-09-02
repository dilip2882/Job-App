package com.dilip.jobsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//@Database(entities = [Result::class], version = 1, exportSchema = false)
//abstract class JobsDatabase : RoomDatabase() {
//    abstract fun jobsDao(): JobsDao
//
//    companion object {
//        const val DATABASE_NAME = "jobs_db"
//
//        @JvmStatic
//        fun getDatabase(context: Context): JobsDatabase {
//            return Room.databaseBuilder(context, JobsDatabase::class.java, DATABASE_NAME)
//                .fallbackToDestructiveMigration()
//                .build()
//        }
//    }
//}