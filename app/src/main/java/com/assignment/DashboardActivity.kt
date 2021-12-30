package com.assignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import assignment.app.R
import com.assignment.utils.setLightStatusText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        setLightStatusText(this)
    }
}
