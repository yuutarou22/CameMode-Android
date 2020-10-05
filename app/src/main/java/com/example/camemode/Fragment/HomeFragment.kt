package com.example.camemode.Fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.camemode.Listener.EndlessScrollListener
import com.example.camemode.Model.UserInfoModel
import com.example.camemode.R
import com.example.camemode.Task.DisplayUtil
import com.example.camemode.Task.SearchUtil
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(), SearchUtil.SearchedListener {

    var userInfoListView = null
    val searchUtil = SearchUtil()
    val displayUtil = DisplayUtil()

    /** FragmentとActivityが紐付けられた時呼び出す(ContextはActivityと同義) */
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    /** Fragment生成時に呼び出す */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /** FragmentがUI描画時に呼び出す */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    /** FragmentのUI描画完了後に呼び出す */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    /** FragmentとActivityの紐付けが解除された時呼び出す */
    override fun onDetach() {
        super.onDetach()
    }

    private fun initView() {

        loading_indicator.visibility = android.widget.ProgressBar.VISIBLE
        searchUtil.setSearchLitener(this)
        searchUtil.searchUserInfo()

        swipe_refresh?.setOnRefreshListener {
            searchUtil.searchUserInfo()
            if (swipe_refresh.isRefreshing()) {
                swipe_refresh.isRefreshing = false
            }
        }

        val endlessScrollListener = EndlessScrollListener(requireContext())
        if (endlessScrollListener != null) {
            user_info_list_home.addOnScrollListener(endlessScrollListener)
        }
    }

    override fun onSuccess(list: ArrayList<UserInfoModel>) {
        activity?.supportFragmentManager?.let {
            displayUtil.displayUserInfo(list, it, context, user_info_list_home)
            loading_indicator.visibility = android.widget.ProgressBar.INVISIBLE
        }
    }
}
