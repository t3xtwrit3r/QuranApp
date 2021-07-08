package com.mubin.quranapp.activity

import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import com.mubin.quranapp.R
import com.mubin.quranapp.databinding.ActivityHomeBinding
import com.mubin.quranapp.fragment.BookmarkFragment
import com.mubin.quranapp.fragment.DailyAyahFragment
import com.mubin.quranapp.fragment.HomeFragment
import com.mubin.quranapp.fragment.NoteFragment

class HomeActivity : AppCompatActivity() {

    private var activityHomeBinding: ActivityHomeBinding? = null
    private var doubleBackToExitPressedOnce = false
    private var ft: FragmentTransaction? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        init()

        activityHomeBinding!!.toolbarTitle.text = "Quran Translation"

        ft = supportFragmentManager.beginTransaction().replace(R.id.frameLayout, HomeFragment())
        ft!!.commit()
    }

    fun init() {
        activityHomeBinding!!.bottomNavigationView.setOnNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    activityHomeBinding!!.toolbarHome.visibility = View.VISIBLE
                    activityHomeBinding!!.toolbarTitle.text = "Quran Translation"
                    ft = supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, HomeFragment())
                    ft!!.commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_chat -> {
                    activityHomeBinding!!.toolbarHome.visibility = View.VISIBLE
                    activityHomeBinding!!.toolbarTitle.text = "Notes"
                    ft = supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, NoteFragment())
                    ft!!.commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_favorites -> {
                    activityHomeBinding!!.toolbarHome.visibility = View.VISIBLE
                    activityHomeBinding!!.toolbarTitle.text = "Bookmarks"
                    ft = supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, BookmarkFragment())
                    ft!!.commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_profile -> {
                    activityHomeBinding!!.toolbarHome.visibility = View.VISIBLE
                    activityHomeBinding!!.toolbarTitle.text = "Daily Ayah"
                    ft = supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, DailyAyahFragment())
                    ft!!.commit()
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    override fun onBackPressed() {
        val backStackCount = supportFragmentManager.backStackEntryCount
        if (doubleBackToExitPressedOnce || backStackCount > 0) {
            super.onBackPressed()
        } else {
            doubleBackToExitPressedOnce = true
            Toast.makeText(applicationContext, "Press again to exit", Toast.LENGTH_SHORT).show()
            Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
        }
    }

}