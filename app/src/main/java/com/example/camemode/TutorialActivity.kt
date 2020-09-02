package com.example.camemode

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.camemode.Adapter.TutorialViewPagerAdapter
import com.example.camemode.Fragment.Tutorial.TutorialPagerFragment01
import com.example.camemode.Fragment.Tutorial.TutorialPagerFragment02
import com.example.camemode.Fragment.Tutorial.TutorialPagerFragment03
import com.example.camemode.Fragment.Tutorial.TutorialPagerFragment04
import kotlinx.android.synthetic.main.activity_tutorial.*

class TutorialActivity : AppCompatActivity() {

    var currentPage: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)
        initView()
    }

    private fun initView() {

        val fragmentList = createFragmentList()


        val adapter = TutorialViewPagerAdapter(supportFragmentManager, fragmentList)
        tutorial_pager.adapter = adapter

        setupIndicator()

    }

    fun onClickBack(view: View) {
        if (currentPage > 0 && currentPage <= 4) {
            currentPage--
        }
        tutorial_pager.currentItem = currentPage
    }

    fun onClickNext(view: View) {
        if (currentPage >= 0 && currentPage < 4) {
            currentPage++
        }
        tutorial_pager.currentItem = currentPage
    }

    private fun createFragmentList(): ArrayList<Fragment> {
        return arrayListOf<Fragment>(
                TutorialPagerFragment01(),
                TutorialPagerFragment02(),
                TutorialPagerFragment03(),
                TutorialPagerFragment04()
        )
    }

    private fun setupIndicator() {
        tutorial_view_pager_indicator.setupWithViewPager(tutorial_pager, true)
        tutorial_view_pager_indicator.clearOnTabSelectedListeners()

        for (view in tutorial_view_pager_indicator.touchables) {
            view.isEnabled = false
        }
    }

    fun onClickNextAppTerm(view: View) {
        val intent = Intent(this, AppTermCheckActivity::class.java)
        startActivity(intent)
        finish()
    }
}
