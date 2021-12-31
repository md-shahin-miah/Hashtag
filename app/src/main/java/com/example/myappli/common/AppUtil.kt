package com.example.myappli.common

import android.os.SystemClock

object AppUtil {
    var mLastClickTime = 0L

    fun isOpenRecently(): Boolean {
        if(SystemClock.elapsedRealtime() - mLastClickTime < 2200){
            return true
        }
        mLastClickTime = SystemClock.elapsedRealtime()
        return false
    }

    var lastClickTime = 0L
    fun isOpenLastSecond(): Boolean {
        if(SystemClock.elapsedRealtime() - lastClickTime < 1000){
            return true
        }
        lastClickTime = SystemClock.elapsedRealtime()
        return false
    }

    fun getNotificationSecond(): Long {
      //  val list = mutableListOf<Long>(60000,90000,120000,150000,180000,210000,240000,270000,300000)
        val list = mutableListOf<Long>(60000,90000,120000,150000)
        list.shuffle()
        return list[0]
    }


}