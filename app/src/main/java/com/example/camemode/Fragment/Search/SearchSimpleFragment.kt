package com.example.camemode.Fragment.Search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.example.camemode.Fragment.BaseFragment
import com.example.camemode.Model.UserInfo.Companion.FIELD_CATEGORY_ROLE
import com.example.camemode.Model.UserInfo.Companion.FIELD_REGION
import com.example.camemode.Model.UserInfoModel

import com.example.camemode.R
import kotlinx.android.synthetic.main.fragment_simple_search.*

class SearchSimpleFragment : BaseFragment() {

    val userInfoModel = UserInfoModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_simple_search, container, false)
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

    private fun initView() {
        search_simple_button_execute?.setOnClickListener {
            val categoryRoleIndex = radio_group.indexOfChild(radio_group.findViewById<RadioButton>(radio_group.checkedRadioButtonId))
            val regionIndex  = spinner_region.selectedItemPosition

            if (categoryRoleIndex.equals(-1)) {
                android.util.Log.d("TEST", "ERROR initView index: " + categoryRoleIndex + ", regionIndex: " + regionIndex)
                // ToDo: エラーダイアログ表示
            } else {
                android.util.Log.d("TEST", "initView index: " + categoryRoleIndex + ", regionIndex: " + regionIndex)
                val bundle = Bundle()
                val searchResultFragment = SearchResultFragment()

                bundle.putInt(FIELD_CATEGORY_ROLE, categoryRoleIndex)
                bundle.putInt(FIELD_REGION, regionIndex)
                searchResultFragment.arguments = bundle

                showFragment(searchResultFragment)
            }
        }
    }
}
