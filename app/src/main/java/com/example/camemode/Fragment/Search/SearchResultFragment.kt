package com.example.camemode.Fragment.Search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.camemode.Fragment.BaseFragment
import com.example.camemode.Listener.EndlessScrollSearchListener
import com.example.camemode.Model.UserInfo.Companion.FIELD_AGE
import com.example.camemode.Model.UserInfo.Companion.FIELD_CATEGORY_ROLE
import com.example.camemode.Model.UserInfo.Companion.FIELD_CHARGE
import com.example.camemode.Model.UserInfo.Companion.FIELD_REGION
import com.example.camemode.Model.UserInfo.Companion.FIELD_SEX
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

    val NONE_VALUE = 5

    var isSimpleSearch = true

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

        // 検索実行時の入力値を取得
        val categoryRoleIndex = arguments?.getInt(FIELD_CATEGORY_ROLE)
        val whichChargeIndex = arguments?.getInt(FIELD_CHARGE, NONE_VALUE)
        val regionIndex = arguments?.getInt(FIELD_REGION)
        val sexIndex = arguments?.getInt(FIELD_SEX, NONE_VALUE)
        val ageIndex = arguments?.getInt(FIELD_AGE, NONE_VALUE)

        val searchConditions = mapOf(
                "categoryRole" to categoryRoleIndex,
                "whichCharge" to whichChargeIndex,
                "region" to regionIndex,
                "sex" to sexIndex,
                "age" to ageIndex
        )

        if (isSimpleSearch(whichChargeIndex, sexIndex, ageIndex)) {
            // １つでもNONE_VALUE（5）だった場合、かんたん検索
            searchUtil.searchUserInfo(categoryRoleIndex, regionIndex)

            swipe_refresh?.setOnRefreshListener {
                searchUtil.searchUserInfo(categoryRoleIndex, regionIndex)
                if (swipe_refresh.isRefreshing()) {
                    swipe_refresh.isRefreshing = false
                }
            }
        } else {
            isSimpleSearch = false
            searchUtil.searchUserInfo(categoryRoleIndex, whichChargeIndex, regionIndex, sexIndex, ageIndex)

            swipe_refresh?.setOnRefreshListener {
                searchUtil.searchUserInfo(categoryRoleIndex, whichChargeIndex, regionIndex, sexIndex, ageIndex)
                if (swipe_refresh.isRefreshing()) {
                    swipe_refresh.isRefreshing = false
                }
            }
        }

        // 検索条件を引数として渡す
        var endlessScrollSearchListener = EndlessScrollSearchListener(requireContext(), isSimpleSearch, searchConditions)
        endlessScrollSearchListener?.let { endlessScrollSearchListener ->
            user_info_list_search_result.addOnScrollListener(endlessScrollSearchListener)
        }
    }

    override fun onSuccess(list: ArrayList<UserInfoModel>) {
        activity?.supportFragmentManager?.let {
            displayUtil.displayUserInfo(list, it, context, user_info_list_search_result)
            loading_indicator.visibility = android.widget.ProgressBar.INVISIBLE

            if (list.isEmpty()) {
                updateNotResultDataVisibility()
            }
        }
    }

    private fun updateNotResultDataVisibility() {
        // 検索結果なしのテキストを表示させる
        no_data_dogeza.visibility = android.widget.TextView.VISIBLE
        no_data_text.visibility = android.widget.TextView.VISIBLE
        no_data_button.visibility = android.widget.Button.VISIBLE
        no_data_button?.setOnClickListener {
            // かんたん検索、詳細検索関わらず、Fragmentを1つ戻す
            fragmentManager?.popBackStack()
        }

        // 以下の処理がないと、ボタンが押下できない
        swipe_refresh.visibility = android.widget.TextView.GONE
        loading_indicator.visibility = android.widget.TextView.GONE
    }

    /**
     * かんたん検索か判定する
     * いずれか１つでもNONE_VALUE(5)だった場合、Trueを返す
     */
    private fun isSimpleSearch(whichChargeIndex: Int?, sexIndex: Int?, ageIndex: Int?): Boolean {
        if (whichChargeIndex == NONE_VALUE || sexIndex == NONE_VALUE || ageIndex == NONE_VALUE) {
            return true
        }
        return false
    }
}
