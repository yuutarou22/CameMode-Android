package com.example.camemode.Fragment.MyPage

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.camemode.Fragment.BaseFragment
import com.example.camemode.Fragment.Dialog.RegistAlertDialogFragment
import com.example.camemode.Fragment.HomeFragment
import com.example.camemode.Model.UserInfo

import com.example.camemode.R
import com.nifcloud.mbaas.core.NCMBException
import com.nifcloud.mbaas.core.NCMBObject
import kotlinx.android.synthetic.main.fragment_my_data_edit.*

class MyDataEditFragment : BaseFragment(), RegistAlertDialogFragment.DialogOkClickListener {

    /**
     * ローカル保持用
     */
    lateinit var data : SharedPreferences
    lateinit var editor : SharedPreferences.Editor

    /**
     * ユーザ情報更新用
     */
    var categoryRoleIndex: Int = 0
    var displayName: String = ""
    var twitterId: String = ""
    var whichChargeIndex: Int = 0
    var region: Int = 0
    var sex: Int = 0
    var age: Int = 0
    var photoImage: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        data = requireContext().getSharedPreferences("UserInfoData", Context.MODE_PRIVATE)
        editor = data.edit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        photo_image_input_edit.setText(data.getString(UserInfo.FIELD_PHOTO_IMAGE, ""), TextView.BufferType.NORMAL)

        update_button.setOnClickListener {
            // Fragmentから単純にDialogFragmentを呼び出せないので一手間かけて呼び出す
            val alertDialogFragment = RegistAlertDialogFragment().newInstance(this)
            fragmentManager?.let { it1 -> alertDialogFragment.show(it1, "aleartDialog") }
        }
    }

    override fun onOkClicked(dialog: DialogFragment) {
        var obj = NCMBObject("UserInfo")

        categoryRoleIndex = category_role.indexOfChild(category_role.findViewById<RadioButton>(category_role.checkedRadioButtonId))
        displayName = editText.text.toString()
        twitterId = editText2.text.toString()
        whichChargeIndex = which_charge.indexOfChild(which_charge.findViewById<RadioButton>(which_charge.checkedRadioButtonId))
        region = region_spinner.selectedItemId.toInt()
        sex = sex_spinner.selectedItemId.toInt()
        age = age_spinner.selectedItemId.toInt()
        photoImage = photo_image_input_edit.text.toString()

        try {
            obj.put("categoryRole", categoryRoleIndex)
            obj.put("displayName", displayName)
            obj.put("twitterId", twitterId)
            obj.put("charge", whichChargeIndex)
            obj.put("region", region)
            obj.put("sex", sex)
            obj.put("age", age)
            obj.put("photoImage", photoImage)

            editor.putInt("categoryRole", categoryRoleIndex)
            editor.putString("displayName", displayName)
            editor.putString("twitterId", twitterId)
            editor.putInt("charge", whichChargeIndex)
            editor.putInt("region", region)
            editor.putInt("sex", sex)
            editor.putInt("age", age)
            editor.putString("photoImage", photoImage)

            // ObjectIdを指定し、登録処理をすると更新処理を実行する
            obj.objectId = data.getString("objectId", "")

            obj.saveInBackground { e ->
                if (e != null) {
                    Log.d("TEST", "保存失敗")
                } else {
                    Log.d("TEST", "保存成功")
                    showFragment(HomeFragment())
                }
            }

        } catch (e: NCMBException) {
            e.printStackTrace()
        }
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
