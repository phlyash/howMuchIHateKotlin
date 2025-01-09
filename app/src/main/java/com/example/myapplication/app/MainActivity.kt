package com.example.myapplication.app

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.features.locomotives.view.LocomotiveCreateFragment
import com.example.myapplication.features.locomotives.view.LocomotiveFragment
import com.example.myapplication.features.trains.view.TrainsFragment
import com.example.myapplication.features.wagons.view.WagonsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var currentFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button_add)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        button.setOnClickListener{ item ->
            when(bottomNav.selectedItemId) {
                R.id.trains -> 1 + 1
                R.id.wagons -> 1 + 1
                R.id.locomotives -> setCurrentFragment(LocomotiveCreateFragment.newInstance())
            }
        }

        @Suppress("DEPRECATION")
        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.trains ->  {
                    currentFragment = TrainsFragment.newInstance()
                    setCurrentFragment(currentFragment)
                }
                R.id.wagons -> {
                    currentFragment = WagonsFragment.newInstance()
                    setCurrentFragment(currentFragment)
                }
                R.id.locomotives -> {
                    currentFragment = LocomotiveFragment.newInstance()
                    setCurrentFragment(currentFragment)
                }
            }
            true
        }
        window.statusBarColor = ContextCompat.getColor(applicationContext, R.color.red)

        currentFragment = TrainsFragment.newInstance()
        setCurrentFragment(currentFragment)
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.flFragment, fragment).commit()
    }
}