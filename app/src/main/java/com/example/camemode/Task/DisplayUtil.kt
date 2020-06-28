package com.example.camemode.Task

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.camemode.Adapter.UserInfoListAdapter
import com.example.camemode.Model.UserInfoModel

class DisplayUtil {
    lateinit var mAdapter : UserInfoListAdapter
    lateinit var viewManager: RecyclerView.LayoutManager

    fun displayUserInfo(
        userInfoList: ArrayList<UserInfoModel>?,
        fragmentManager: FragmentManager,
        context: Context?,
        user_info_list: RecyclerView) {

        if (context != null && userInfoList != null) {
            viewManager = LinearLayoutManager(context.applicationContext)
            mAdapter = UserInfoListAdapter(userInfoList, fragmentManager, context)

            user_info_list.apply {
                setHasFixedSize(true)
                layoutManager = viewManager
                adapter = mAdapter
            }
        }
    }
}