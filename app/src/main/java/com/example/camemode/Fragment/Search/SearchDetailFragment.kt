package com.example.camemode.Fragment.Search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.example.camemode.Fragment.BaseFragment
import com.example.camemode.Model.UserInfo.Companion.FIELD_AGE
import com.example.camemode.Model.UserInfo.Companion.FIELD_CATEGORY_ROLE
import com.example.camemode.Model.UserInfo.Companion.FIELD_CHARGE
import com.example.camemode.Model.UserInfo.Companion.FIELD_REGION
import com.example.camemode.Model.UserInfo.Companion.FIELD_SEX

import com.example.camemode.R
import kotlinx.android.synthetic.main.fragment_search_detail.*

class SearchDetailFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_detail, container, false)
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
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.search_detail_buttin)

        search_detail_button_execute.setOnClickListener {
            val categoryRoleIndex = category_role.indexOfChild(category_role.findViewById<RadioButton>(category_role.checkedRadioButtonId))
            val whichChargeIndex = which_charge.indexOfChild(which_charge.findViewById<RadioButton>(which_charge.checkedRadioButtonId))
            val regionIndex  = region_spinner.selectedItemPosition
            val sexIndex = sex_spinner.selectedItemPosition
            val ageIndex = age_spinner.selectedItemPosition

            android.util.Log.d("TEST","SearchDetailFragment initView categoryRoleIndex: " + categoryRoleIndex + ", whichChargeIndex: " + whichChargeIndex + ", regionIndex: " + regionIndex + ", sexIndex: " + sexIndex + ", ageIndex: " + ageIndex)

            val bundle = Bundle()
            val searchResultFragment = SearchResultFragment()

            bundle.putInt(FIELD_CATEGORY_ROLE, categoryRoleIndex)
            bundle.putInt(FIELD_CHARGE, whichChargeIndex)
            bundle.putInt(FIELD_REGION, regionIndex)
            bundle.putInt(FIELD_SEX, sexIndex)
            bundle.putInt(FIELD_AGE, ageIndex)

            searchResultFragment.arguments = bundle
            showFragment(searchResultFragment)
        }
    }
}
