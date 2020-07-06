package com.example.camemode.Fragment.Search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.camemode.Fragment.BaseFragment
import com.example.camemode.Model.UserInfo.Companion.FIELD_REGION
import com.example.camemode.Model.UserInfoModel

import com.example.camemode.R
import com.example.camemode.Task.DisplayUtil
import com.example.camemode.Task.SearchUtil
import kotlinx.android.synthetic.main.fragment_home.loading_indicator
import kotlinx.android.synthetic.main.fragment_home.swipe_refresh
import kotlinx.android.synthetic.main.fragment_search_result.*

class SearchResultFragment : BaseFragment(), SearchUtil.SearchedListener {
    val searchUtil = SearchUtil()
    val displayUtil = DisplayUtil()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    fun initView() {
        loading_indicator.visibility = android.widget.ProgressBar.VISIBLE
        searchUtil.setSearchLitener(this)

        val categoryRole = arguments?.getInt(FIELD_REGION)
        val region = arguments?.getInt(FIELD_REGION)

        searchUtil.searchUserInfo(categoryRole, region)

        swipe_refresh?.setOnRefreshListener {
            searchUtil.searchUserInfo()
            if (swipe_refresh.isRefreshing()) {
                swipe_refresh.isRefreshing = false
            }
        }
    }

    override fun onSuccess(list: ArrayList<UserInfoModel>) {
        activity?.supportFragmentManager?.let {
            displayUtil.displayUserInfo(list, it, context, user_info_list_search_result)
            loading_indicator.visibility = android.widget.ProgressBar.INVISIBLE
        }
    }
}
