package com.example.myapplication.app

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.features.locomotives.view.LocomotiveCreateFragment
import com.example.myapplication.features.locomotives.view.LocomotiveFragment
import com.example.myapplication.features.statistics.view.StatisticsFragment
import com.example.myapplication.features.trains.view.TrainCreateFragment
import com.example.myapplication.features.trains.view.TrainsFragment
import com.example.myapplication.features.wagons.view.WagonFragmentCreate
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
                R.id.trains -> setCurrentFragment(TrainCreateFragment.newInstance())
                R.id.wagons -> setCurrentFragment(WagonFragmentCreate.newInstance())
                R.id.locomotives -> setCurrentFragment(LocomotiveCreateFragment.newInstance())
            }
        }

        @Suppress("DEPRECATION")
        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.trains ->  {
                    button.visibility = View.VISIBLE
                    currentFragment = TrainsFragment.newInstance()
                    setCurrentFragment(currentFragment)
                }
                R.id.wagons -> {
                    button.visibility = View.VISIBLE
                    currentFragment = WagonsFragment.newInstance()
                    setCurrentFragment(currentFragment)
                }
                R.id.locomotives -> {
                    button.visibility = View.VISIBLE
                    currentFragment = LocomotiveFragment.newInstance()
                    setCurrentFragment(currentFragment)
                }
                R.id.statistics -> {
                    button.visibility = View.GONE
                    currentFragment = StatisticsFragment.newInstance()
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