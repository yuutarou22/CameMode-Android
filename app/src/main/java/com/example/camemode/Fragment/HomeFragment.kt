package com.example.camemode.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.camemode.R
import com.nifcloud.mbaas.core.NCMBObject
import com.nifcloud.mbaas.core.NCMBQuery
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    var userInfoListView = null

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
        swipe_refresh?.setOnRefreshListener {
            search()
        }
    }

    private fun search() {
        var query = NCMBQuery<NCMBObject>("UserInfo")
        query.addOrderByDescending("updateDate")
        query.setLimit(15)
        query.findInBackground { mutableList, ncmbException ->
            if (ncmbException != null) {
                Log.d("ERROR", "NCMB findInBackground error: " + ncmbException.message)
            } else {
                for (obj in mutableList) {
                    Log.d("TEST", obj.getString("displayName"))
                }
            }
        }
        if (swipe_refresh.isRefreshing()) {
            swipe_refresh.isRefreshing = false
        }
    }
}
