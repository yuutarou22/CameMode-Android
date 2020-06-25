package com.example.camemode

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

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                showFragment(HomeFragment())
                return@OnNavigationItemSelectedListener true
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
//        var obj:NCMBObject = NCMBObject("UserInfo")
        initView()
    }

    private fun initView() {
        setContentView(R.layout.activity_main)
        showFragment(HomeFragment())
        val navView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
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
