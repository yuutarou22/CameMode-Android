package com.example.camemode

import android.content.Context
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
     * 登録用変数
     */
    var categoryRoleIndex: Int = 0
    var displayName: String = ""
    var twitterId: String = ""
    var whichChargeIndex: Int = 0
    var region: Long = 0
    var sex: Long = 0
    var age: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
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
                    INDICATOR_SIZE -> next_button.setText("登録する")
                    else -> {
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
                }
                1 -> {
                    if (display_name_input.text.isEmpty()) {
                        display_name_input.setError("表示名を入力してください！")
                        display_name_input.isFocusable = true
                        validation_result = false
                    }
                    if (twitter_id_input.text.isEmpty()) {
                        twitter_id_input.setError("TwitterIDを入力してください！")
                        twitter_id_input.isFocusable = true
                        validation_result = false
                    }
                    displayName = display_name_input.text.toString()
                    twitterId = twitter_id_input.text.toString()
                }
                2 -> {
                    whichChargeIndex = which_charge.indexOfChild(which_charge.findViewById<RadioButton>(which_charge.checkedRadioButtonId))
                    region = region_spinner.selectedItemId
                    sex = sex_spinner.selectedItemId
                    age = age_spinner.selectedItemId
                }
                3 -> {
                    if (photo_image_input.text.isEmpty()) {
                        photo_image_input.setError("撮影イメージを入力してください！")
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
            obj.put("charge", whichChargeIndex)
            obj.put("region", region)
            obj.put("sex", sex)
            obj.put("age", age)
            obj.put("photoImage", photo_image_input.text.toString())

            obj.saveInBackground { e ->
                if (e != null) {
                    Log.d("TEST", "保存失敗")
                } else {
                    Log.d("TEST", "保存成功")
                    // Todo: ローカルにもユーザ情報を保持する（登録しているフラグをたて、ユーザ情報+ObjectId）
//                    showFragment(HomeFragment())
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
