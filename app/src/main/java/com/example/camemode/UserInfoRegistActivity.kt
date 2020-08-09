package com.example.camemode

import androidx.fragment.app.Fragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager
import com.example.camemode.Adapter.UserInfoRegistViewPagerAdapter
import com.example.camemode.Fragment.UserInfoRegist.UserInfoRegistFragment01
import com.example.camemode.Fragment.UserInfoRegist.UserInfoRegistFragment02
import com.example.camemode.Fragment.UserInfoRegist.UserInfoRegistFragment03
import com.example.camemode.Fragment.UserInfoRegist.UserInfoRegistFragment04
import kotlinx.android.synthetic.main.activity_user_info_regist.*

class UserInfoRegistActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info_regist)

        initView()
    }

    private fun initView() {
        // フラグメントのリストを作成
        val fragmentList = createFragmentList()

        // アダプタのインスタンス生成、アダプタのセット
        val adapter = UserInfoRegistViewPagerAdapter(supportFragmentManager, fragmentList)
        user_info_regist_view_pager.adapter = adapter

        // スワイプの無効
        user_info_regist_view_pager.setPagingEnabled(false)

        // インジケータの設定
        user_info_regist_view_pager_indicator.setupWithViewPager(user_info_regist_view_pager)

        user_info_regist_view_pager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    back_button.setText("マイページへ戻る")
                } else {
                    back_button.setText("戻る")
                }
            }
        })

        back_button.setOnClickListener {
            if (user_info_regist_view_pager.currentItem == 0) {
                finish()
            } else {
                user_info_regist_view_pager.currentItem -= 1
            }
        }

        next_button.setOnClickListener {
            user_info_regist_view_pager.currentItem += 1
        }
    }

    private fun createFragmentList(): ArrayList<Fragment> {
        return arrayListOf<Fragment>(
            UserInfoRegistFragment01(),
            UserInfoRegistFragment02(),
            UserInfoRegistFragment03(),
            UserInfoRegistFragment04()
        )
    }
}
