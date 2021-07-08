package com.mubin.quranapp.activity

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mubin.quranapp.R
import com.mubin.quranapp.activity.HomeActivity
import com.mubin.quranapp.activity.SplashActivity
import com.mubin.quranapp.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    var activitySplashBinding: ActivitySplashBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySplashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        hideStatusBar(window, false)
        val animation = AnimationUtils.loadAnimation(this, R.anim.slide_in2)
        activitySplashBinding!!.startBTN.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }

    companion object {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        fun hideStatusBar(window: Window, darkText: Boolean) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
            var flag = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && darkText) {
                flag = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
            window.decorView.systemUiVisibility = flag or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
    }
}