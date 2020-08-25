package com.example.camemode.Fragment.MyPage

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.camemode.Fragment.BaseFragment

import com.example.camemode.R
class MyDataEditFragment : BaseFragment(), RegistAlertDialogFragment.DialogOkClickListener {

    /**
     * ローカル保持用
     */
    lateinit var data : SharedPreferences
    lateinit var editor : SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        data = context!!.getSharedPreferences("UserInfoData", Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_data_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        category_role.check(getCheckCategoryRoleRadioButtonId())
        editText.setText(data.getString(UserInfo.FIELD_DISPLAY_NAME, ""), TextView.BufferType.NORMAL)
        editText2.setText(data.getString(UserInfo.FIELD_TWITTER_ID, ""), TextView.BufferType.NORMAL)
        which_charge.check(getCheckChargeRadioButtonId())
        region_spinner.setSelection(data.getInt(UserInfo.FIELD_REGION, 1))
        sex_spinner.setSelection(data.getInt(UserInfo.FIELD_SEX, 1))
        age_spinner.setSelection(data.getInt(UserInfo.FIELD_AGE, 1))
        imagination_hope.setText(data.getString(UserInfo.FIELD_PHOTO_IMAGE, ""), TextView.BufferType.NORMAL)

        update_button.setOnClickListener {
            val alertDialogFragment = RegistAlertDialogFragment().newInstance(this)
            fragmentManager?.let { it1 -> alertDialogFragment.show(it1, "aleartDialog") }
        }
    }

    override fun onOkClicked(dialog: DialogFragment) {
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    private fun getCheckCategoryRoleRadioButtonId(): Int {
        if (0 == data.getInt(UserInfo.FIELD_CATEGORY_ROLE, 1)) {
            return R.id.category_role_cameraman_edit
        } else if(1 == data.getInt(UserInfo.FIELD_CATEGORY_ROLE, 1)){
            return R.id.category_role_model_edit
        } else {
            return R.id.category_role_none_edit
        }
    }

    private fun getCheckChargeRadioButtonId(): Int {
        if (0 == data.getInt(UserInfo.FIELD_CHARGE, 1)) {
            return R.id.no_charge_edit
        } else if(1 == data.getInt(UserInfo.FIELD_CHARGE, 1)) {
            return R.id.charge_edit
        } else {
            return R.id.charge_either_edit
        }
    }
}
