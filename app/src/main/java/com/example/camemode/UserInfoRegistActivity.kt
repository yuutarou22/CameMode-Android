package com.example.camemode

import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment
import androidx.viewpager.widget.ViewPager
import com.example.camemode.Adapter.UserInfoRegistViewPagerAdapter
import com.example.camemode.Fragment.Dialog.RegistAlertDialogFragment
import com.example.camemode.Fragment.UserInfoRegist.UserInfoRegistFragment01
import com.example.camemode.Fragment.UserInfoRegist.UserInfoRegistFragment02
import com.example.camemode.Fragment.UserInfoRegist.UserInfoRegistFragment03
import com.example.camemode.Fragment.UserInfoRegist.UserInfoRegistFragment04
import com.nifcloud.mbaas.core.NCMBException
import com.nifcloud.mbaas.core.NCMBObject
import kotlinx.android.synthetic.main.activity_user_info_regist.*
import kotlinx.android.synthetic.main.fragment_user_info_regist_01.*
import kotlinx.android.synthetic.main.fragment_user_info_regist_02.*
import kotlinx.android.synthetic.main.fragment_user_info_regist_03.*
import kotlinx.android.synthetic.main.fragment_user_info_regist_04.*

class UserInfoRegistActivity : AppCompatActivity(), RegistAlertDialogFragment.DialogOkClickListener {

    /**
     * ローカル保持用
     */
    lateinit var data : SharedPreferences
    lateinit var editor : SharedPreferences.Editor

    /**
     * 登録用変数
     */
    var categoryRoleIndex: Int = 0
    var displayName: String = ""
    var twitterId: String = ""
    var instagramId: String = ""
    var whichChargeIndex: Int = 0
    var region: Int = 0
    var sex: Int = 0
    var age: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        data = getSharedPreferences("UserInfoData", Context.MODE_PRIVATE)
        editor = data.edit()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info_regist)

        initView()
    }

    private fun initView() {
        // フラグメントのリストを作成
        val fragmentList = createFragmentList()

        // アダプタのインスタンス生成、アダプタのセット
        val adapter = UserInfoRegistViewPagerAdapter(supportFragmentManager, fragmentList)
        user_info_regist_view_pager.adapter = adapter

        // スワイプの無効
        user_info_regist_view_pager.setPagingEnabled(false)

        // インジケータの設定
        setupIndicator()

        user_info_regist_view_pager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                val INDICATOR_SIZE = fragmentList.size - 1

                when (position) {
                    0 -> back_button.setText("マイページへ戻る")
                    INDICATOR_SIZE -> {
                        next_button.setText("登録する")
                        next_button.setBackgroundColor(getColor(R.color.colorThema))
                        next_button.setTextColor(getColor(R.color.colorContrast))
                    }
                    else -> {
                        next_button.setBackgroundColor(getColor(R.color.colorContrast))
                        next_button.setTextColor(getColor(R.color.colorWhite))
                        next_button.setText("次へ")
                        back_button.setText("戻る")
                    }
                }
            }
        })

        back_button.setOnClickListener {
            if (user_info_regist_view_pager.currentItem == 0) {
                finish()
            } else {
                user_info_regist_view_pager.currentItem -= 1
            }
        }

        next_button.setOnClickListener {
            var validation_result = true
            when (user_info_regist_view_pager.currentItem) {
                0 -> {
                    categoryRoleIndex = category_role.indexOfChild(category_role.findViewById<RadioButton>(category_role.checkedRadioButtonId))

                    // 0文字または空白のみの場合
                    if (display_name_input.text.isNullOrBlank()) {
                        display_name_input.setError(getString(R.string.regist_display_name_empty_error))
                        display_name_input.isFocusable = true
                        validation_result = false
                    }
                    displayName = display_name_input.text.toString()
                }
                1 -> {
                    if (twitter_id_input.text.isNullOrBlank() && instagram_id_input.text.isNullOrBlank()) {
                        twitter_id_input.setError(getString(R.string.regist_sns_id_empty_error))
                        instagram_id_input.setError(getString(R.string.regist_sns_id_empty_error))
                        twitter_id_input.isFocusable = true
                        validation_result = false
                    } else {
                        twitter_id_input.clearFocus()
                        instagram_id_input.clearFocus()
                    }
                    twitterId = twitter_id_input.text.toString()
                    instagramId = instagram_id_input.text.toString()
                }
                2 -> {
                    whichChargeIndex = which_charge.indexOfChild(which_charge.findViewById<RadioButton>(which_charge.checkedRadioButtonId))
                    region = region_spinner.selectedItemId.toInt()
                    sex = sex_spinner.selectedItemId.toInt()
                    age = age_spinner.selectedItemId.toInt()
                }
                3 -> {
                    if (photo_image_input.text.isEmpty()) {
                        photo_image_input.setError(getString(R.string.regist_photo_image_empty_error))
                        photo_image_input.isFocusable = true
                        validation_result = false
                    } else if (photo_image_input.text.length > 200) {
                        photo_image_input.setError(getString(R.string.regist_photo_image_over_error))
                        photo_image_input.isFocusable = true
                        validation_result = false
                    }
                }
                else -> {}
            }
            if (validation_result) {
                if (user_info_regist_view_pager.currentItem == 3) {
                    val alertDialogFragment = RegistAlertDialogFragment()
                    alertDialogFragment.show(supportFragmentManager, "aleartDialog")
                }
                user_info_regist_view_pager.currentItem += 1
            }
        }
    }

    /**
     * 登録確認ダイアログのOKボタン押下後の処理
     */
    override fun onOkClicked(dialog: DialogFragment) {
        var obj = NCMBObject("UserInfo")
        try {
            obj.put("categoryRole", categoryRoleIndex)
            obj.put("displayName", displayName)
            obj.put("twitterId", twitterId)
            obj.put("instagramId", instagramId)
            obj.put("charge", whichChargeIndex)
            obj.put("region", region)
            obj.put("sex", sex)
            obj.put("age", age)
            obj.put("photoImage", photo_image_input.text.toString())

            editor.putInt("categoryRole", categoryRoleIndex)
            editor.putString("displayName", displayName)
            editor.putString("twitterId", twitterId)
            editor.putString("instagramId", instagramId)
            editor.putInt("charge", whichChargeIndex)
            editor.putInt("region", region)
            editor.putInt("sex", sex)
            editor.putInt("age", age)
            editor.putString("photoImage", photo_image_input.text.toString())

            obj.saveInBackground { e ->
                if (e != null) {
                    Log.d("TEST", "保存失敗")
                } else {
                    Log.d("TEST", "保存成功")
                    editor.putString("objectId", obj.objectId)
                    editor.commit()
                    finish()
                }
            }
        } catch (e: NCMBException) {
            e.printStackTrace()
        }
    }

    private fun createFragmentList(): ArrayList<Fragment> {
        return arrayListOf<Fragment>(
            UserInfoRegistFragment01(),
            UserInfoRegistFragment02(),
            UserInfoRegistFragment03(),
            UserInfoRegistFragment04()
        )
    }

    private fun setupIndicator() {
        user_info_regist_view_pager_indicator.setupWithViewPager(user_info_regist_view_pager, true)
        user_info_regist_view_pager_indicator.clearOnTabSelectedListeners()

        for (view in user_info_regist_view_pager_indicator.touchables) {
            view.isEnabled = false
        }
    }
}
