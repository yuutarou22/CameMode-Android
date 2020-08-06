package com.example.camemode

import androidx.fragment.app.Fragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
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


        // フラグメントのリストを作成
        val fragmentList = arrayListOf<Fragment>(
            UserInfoRegistFragment01(),
            UserInfoRegistFragment02(),
            UserInfoRegistFragment03(),
            UserInfoRegistFragment04()
        )

        // アダプタのインスタンス生成、アダプタのセット
        val adapter = UserInfoRegistViewPagerAdapter(supportFragmentManager, fragmentList)
        user_info_regist_view_pager.adapter = adapter
    }

}
