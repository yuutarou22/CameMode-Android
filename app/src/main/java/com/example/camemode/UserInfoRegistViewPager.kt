package com.example.camemode

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class UserInfoRegistViewPager(context: Context, attrs: AttributeSet?) : ViewPager(context, attrs) {
    private var isEnable = true

    override fun onTouchEvent(ev: MotionEvent?): Boolean = when (isEnable) {
        true -> super.onTouchEvent(ev)
        else -> false
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean = when (isEnable) {
        true -> super.onInterceptTouchEvent(ev)
        else -> false
    }

    fun setPagingEnabled(isEnable: Boolean) {
        this.isEnable = isEnable
    }
}