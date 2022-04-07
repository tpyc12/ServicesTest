package com.myhome.android.servicestest

import android.content.Context
import android.util.Log
import androidx.constraintlayout.widget.ConstraintSet
import androidx.work.*

class MyWorker(
    context: Context,
    private val workerParameters: WorkerParameters
): Worker(context, workerParameters) {

    override fun doWork(): Result {
        log("onHandleIntent")
        val page = workerParameters.inputData.getInt(PAGE, 0)
        for (i in 0 until 3) {
            Thread.sleep(1000)
            log("Timer $i $page")
        }
        return Result.success()
    }

    private fun log(message: String) {
        Log.d("SERVICE_TAG", "MyJobIntentService: $message")
    }

    companion object{

        private const val PAGE = "page"
        const val WORK_NAME = "work_name"

        fun makeRequest(page: Int): OneTimeWorkRequest{
            return OneTimeWorkRequestBuilder<MyWorker>()
                .setInputData(workDataOf(PAGE to page))
                .setConstraints(makeConstraints())
                .build()
        }

        private fun makeConstraints(): Constraints{
            return Constraints.Builder()
                .setRequiresCharging(true)
                .build()
        }
    }
}