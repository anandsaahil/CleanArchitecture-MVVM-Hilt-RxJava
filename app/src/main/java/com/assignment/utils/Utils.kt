package com.assignment.utils

import androidx.core.view.ViewCompat
import androidx.fragment.app.FragmentActivity

fun setLightStatusText(activity: FragmentActivity){
    val windowInsetController = ViewCompat.getWindowInsetsController(activity.window.decorView)
    windowInsetController?.isAppearanceLightStatusBars = false
}