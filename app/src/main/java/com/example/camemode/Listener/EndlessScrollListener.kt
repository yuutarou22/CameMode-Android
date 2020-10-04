package com.example.camemode.Listener

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.camemode.Model.UserInfoModel
import com.example.camemode.Task.DisplayUtil
import com.example.camemode.Task.SearchUtil
import kotlinx.android.synthetic.main.fragment_home.*

class EndlessScrollListener(context: Context): RecyclerView.OnScrollListener(), SearchUtil.SearchedListener{

    val context = context

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (!recyclerView.canScrollVertically(-1)) {
            // リストの先頭の場合
            android.util.Log.d("EndlessScrollListener", "onScrolled Top")
        }

        if (!recyclerView.canScrollVertically(1)) {
            // リストの末尾の場合
            android.util.Log.d("EndlessScrollListener", "onScrolled Bottom")

            // 取得したユーザ情報の最古情報を取得する
            val searchUtil = SearchUtil()
            searchUtil.setSearchLitener(this)
            searchUtil.searchUserInfoAuto()
        }
    }

    override fun onSuccess(list: ArrayList<UserInfoModel>) {
        android.util.Log.d("EndlessScrollListener", "onSuccess")

        var displayUtil = DisplayUtil()

        (context as FragmentActivity).supportFragmentManager?.let { displayUtil.displayUserInfo(list, it, context, context.user_info_list_home) }
        context.loading_indicator.visibility = android.widget.ProgressBar.INVISIBLE
    }
}