package com.example.camemode

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.camemode.Fragment.HomeFragment
import com.example.camemode.Fragment.MyPage.MyPageFragment
import com.example.camemode.Fragment.Search.SearchFragment
import com.nifcloud.mbaas.core.NCMB

class MainActivity : AppCompatActivity() {

    private lateinit var currentFlagment: Fragment
    val fragmentManager = supportFragmentManager

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                // HomeFragmentを開いている場合、再度読み込まない
                if (fragmentManager.fragments.get(0) !is HomeFragment ) {
                    showFragment(HomeFragment())
                    return@OnNavigationItemSelectedListener true
                }
            }
            R.id.navigation_search -> {
                showFragment(SearchFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_mypage -> {
                showFragment(MyPageFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NCMB.initialize(this.applicationContext, Config.KEY_APPLICATION, Config.KEY_CLIENT)
        initView()
    }

    private fun initView() {
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)

        val data = getSharedPreferences("status", Context.MODE_PRIVATE)

        if (!data.getBoolean("isTutorialAndAppTermFinished", false)) {
            val intent = Intent(this, TutorialActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            showFragment(HomeFragment())
            val navView: BottomNavigationView = findViewById(R.id.bottom_navigation)
            navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        }
    }

    fun showFragment(f: Fragment) {
        currentFlagment = f
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.replace(R.id.main_content, f)
        fragmentTransaction.commit()
    }

}
