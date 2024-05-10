package com.example.mynewsapplicationproject.data

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.mynewsapplicationproject.data.repository.TopHeadlineRepository
import com.example.mynewsapplicationproject.utils.AppConstant
import javax.inject.Inject

class MyNewsFetchWork @Inject constructor(context: Context,
                                          workParams: WorkerParameters,
                                          private val repo: TopHeadlineRepository)
    : Worker(context, workParams) {

    companion object {
        const val WORK_MANAGER_TAG = "MyNewsFetchWork"
    }

    @SuppressLint("RestrictedApi")
    override fun doWork(): Result {
        return try {
            repo.getTopHeadlines(AppConstant.COUNTRY)
            Log.d(WORK_MANAGER_TAG, "doWork: work manager action successful")
            Result.Success()
        }
        catch (e: Error) {
            Log.d(WORK_MANAGER_TAG, "doWork: error " + e.message)
            Result.failure()
        }
    }
}