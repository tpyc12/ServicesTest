package com.myhome.android.servicestest

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyIntentServiceForJob : IntentService(NAME) {

    override fun onCreate() {
        super.onCreate()
        log("onCreate")
        setIntentRedelivery(true)
    }

    override fun onHandleIntent(p0: Intent?) {
        log("onHandleIntent")
        val page = p0?.getIntExtra(PAGE, 0) ?: 0
        for (i in 0 until 3) {
            Thread.sleep(1000)
            log("Timer $i")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    private fun log(message: String) {
        Log.d("SERVICE_TAG", "MyIntentServiceForJob: $message")
    }

    companion object {

        private const val NAME = "MyIntentServiceForJob"
        private const val PAGE = "page"

        fun newIntent(context: Context, page: Int): Intent {
            return Intent(context, MyIntentServiceForJob::class.java).apply {
                putExtra(PAGE, page)
            }
        }
    }
}