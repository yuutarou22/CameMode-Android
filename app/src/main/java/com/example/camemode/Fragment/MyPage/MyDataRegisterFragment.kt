package com.example.camemode.Fragment.MyPage

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import com.example.camemode.Fragment.BaseFragment
import com.example.camemode.Fragment.Dialog.RegistAlertDialogFragment
import com.example.camemode.Fragment.HomeFragment
import com.example.camemode.Interface.TextWatchable

import com.example.camemode.R
import com.nifcloud.mbaas.core.NCMBException
import com.nifcloud.mbaas.core.NCMBObject
import kotlinx.android.synthetic.main.fragment_my_data_register.*
import kotlinx.android.synthetic.main.fragment_my_data_register.regist_button

class MyDataRegisterFragment : BaseFragment(), RegistAlertDialogFragment.DialogOkClickListener {

    override fun onOkClicked(dialog: DialogFragment) {
        
        var obj = NCMBObject("UserInfo")
        try {
            val categoryRoleIndex = category_role.indexOfChild(category_role.findViewById<RadioButton>(category_role.checkedRadioButtonId))
            val whichChargeIndex = which_charge.indexOfChild(which_charge.findViewById<RadioButton>(which_charge.checkedRadioButtonId))

            obj.put("categoryRole", categoryRoleIndex)
            obj.put("displayName", display_name_input.text.toString())
            obj.put("photoImage", photo_image_input.text.toString())
            obj.put("twitterId", twitter_id_input.text.toString())
            obj.put("age", age_spinner.selectedItemId)
            obj.put("region", region_spinner.selectedItemId)
            obj.put("sex", sex_spinner.selectedItemId)
            obj.put("charge", whichChargeIndex)

            obj.saveInBackground { e ->
                if (e != null) {
                    Log.d("TEST","保存失敗")
                } else {
                    Log.d("TEST","保存成功")
                    showFragment(HomeFragment())
                }
            }
        } catch (e: NCMBException) {
            e.printStackTrace()
        }
    }

    companion object {
        const val MAX_PHOTO_IMAGE_LENGTH = 200
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_data_register, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initView() {

        photo_image_input.addTextChangedListener(object: TextWatchable{
            override fun onTextChanged(str: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var textColor: Int = Color.GRAY
                var textLength: Int = str!!.length
                photo_image_text_count.text = "${textLength}/200"
                if (textLength >= MAX_PHOTO_IMAGE_LENGTH)
                    textColor = Color.RED
                photo_image_text_count.setTextColor(textColor)
            }
        })

        regist_button.setOnClickListener {
            // バリデーションチェックし、問題なければ確認ダイアログを表示する
            if (userInfoValidationCheck()) {
                val alertDialogFragment = RegistAlertDialogFragment()
                alertDialogFragment.setTargetFragment(this, 1)
                fragmentManager?.let { it1 -> alertDialogFragment.show(it1, "") }
            }
        }
    }

    /**
     * バリデーションチェック
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun userInfoValidationCheck() : Boolean {

        var result = true

        if (display_name_input.text.isEmpty()) {
            display_name_input.setError("表示名を入力してください！")
            display_name_input.focusable = View.FOCUSABLE
            result = false
        }

        if (twitter_id_input.text.isEmpty()) {
            twitter_id_input.setError("TwitterIDを入力してください！")
            twitter_id_input.focusable = View.FOCUSABLE
            result = false
        }

        if (photo_image_input.text.isEmpty()) {
            photo_image_input.setError("撮影イメージを入力してください！")
            photo_image_input.focusable = View.FOCUSABLE
            result = false
        }

        return result
    }
}
