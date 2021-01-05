package com.example.camemode.Listener

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.camemode.Model.UserInfoModel
import com.example.camemode.Task.DisplayUtil
import com.example.camemode.Task.SearchUtil
import kotlinx.android.synthetic.main.fragment_home.*

class EndlessScrollSearchListener(context: Context,
                                  isSimpleSearch: Boolean,
                                  searchConditions: Map<String, Int?>):
    RecyclerView.OnScrollListener(),
    SearchUtil.SearchedListener{

    val context = context
    val isSimpleSearch = isSimpleSearch
    val searchConditions = searchConditions

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (!recyclerView.canScrollVertically(-1)) {
            // リストの先頭の場合
            android.util.Log.d("EndlessScrollSearchListener", "onScrolled Top")
        }

        if (!recyclerView.canScrollVertically(1)) {
            // リストの末尾の場合
            android.util.Log.d("EndlessScrollSearchListener", "onScrolled Bottom")

            // 取得したユーザ情報の最古情報を取得する
            val searchUtil = SearchUtil()
            searchUtil.setSearchLitener(this)

            if (isSimpleSearch) {
                // かんたん検索
                searchUtil.searchUserInfoAuto(searchConditions["categoryRole"], searchConditions["region"])
            } else {
                // 詳細検索
                searchUtil.searchUserInfoAuto(
                    searchConditions["categoryRole"],
                    searchConditions["whichCharge"],
                    searchConditions["region"],
                    searchConditions["sex"],
                    searchConditions["age"])
            }
        }
    }

    override fun onSuccess(list: ArrayList<UserInfoModel>) {
        android.util.Log.d("EndlessScrollSearchListener", "onSuccess")

        var displayUtil = DisplayUtil()

        (context as FragmentActivity).supportFragmentManager?.let { displayUtil.displayUserInfo(list) }
        context.loading_indicator.visibility = android.widget.ProgressBar.INVISIBLE
    }
}