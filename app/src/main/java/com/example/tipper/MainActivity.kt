package com.example.tipper

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.tipper.databinding.ActivityMainBinding
import com.example.tipper.quiz.QuizFragment

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        replaceFragment(HomeFragment())
        binding!!.bottomNavigationView.setOnNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.home -> replaceFragment(HomeFragment())
                R.id.chart -> replaceFragment(BMIChartFragment())
                R.id.bmr -> replaceFragment(BMRFragment())
                R.id.recipes -> replaceFragment(RecipesFragment())
                R.id.quiz -> replaceFragment(QuizFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}