package com.example.camemode.Fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.camemode.Model.UserInfo
import com.example.camemode.Model.UserInfo.Companion.FIELD_AGE
import com.example.camemode.Model.UserInfo.Companion.FIELD_CATEGORY_ROLE
import com.example.camemode.Model.UserInfo.Companion.FIELD_CHARGE
import com.example.camemode.Model.UserInfo.Companion.FIELD_DISPLAY_NAME
import com.example.camemode.Model.UserInfo.Companion.FIELD_PHOTO_IMAGE
import com.example.camemode.Model.UserInfo.Companion.FIELD_REGION
import com.example.camemode.Model.UserInfo.Companion.FIELD_SEX

import com.example.camemode.R
import kotlinx.android.synthetic.main.content_user_info_age.*
import kotlinx.android.synthetic.main.content_user_info_charge.*
import kotlinx.android.synthetic.main.content_user_info_photo_image.*
import kotlinx.android.synthetic.main.content_user_info_region.*
import kotlinx.android.synthetic.main.content_user_info_sex.*
import kotlinx.android.synthetic.main.fragment_user_info_detail.*

class UserInfoDetailFragment : BaseFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_info_detail, container, false)
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
        (activity as AppCompatActivity).supportActionBar?.hide()
        region.text = getRegion(arguments?.getInt(FIELD_REGION))
        category_role_icon.setImageResource(getUserIcon(arguments?.getInt(FIELD_CATEGORY_ROLE)))
        category_role_icon.setBackgroundColor(getUserIconBg(arguments?.getInt(FIELD_CATEGORY_ROLE)))
        category_role_text.text = getCategoryRole(arguments?.getInt(FIELD_CATEGORY_ROLE))
        charge.text = getCharge(arguments?.getInt(FIELD_CHARGE))
        age.text = getAge(arguments?.getInt(FIELD_AGE))
        display_name.text = arguments?.getString(FIELD_DISPLAY_NAME)
        photo_image.text = arguments?.getString(FIELD_PHOTO_IMAGE)
        sex.text = getSex(arguments?.getInt(FIELD_SEX))

        back_button.setOnClickListener {
            fragmentManager?.popBackStack()
            (activity as AppCompatActivity).supportActionBar?.show()
        }

        setupSnsButton(
            arguments?.getString(UserInfo.FIELD_TWITTER_ID).toString(),
            arguments?.getString(UserInfo.FIELD_INSTAGRAM_ID).toString())
    }

    /**
     * カテゴリ種別を文字列に変換
     * @param categoryRole
     * @return String
     */
    private fun getCategoryRole(categoryRole: Int?): String {
        return when(categoryRole) {
            0 -> getString(R.string.category_role_camera)
            1 -> getString(R.string.category_role_model)
            else -> getString(R.string.category_role_camera_model)
        }
    }

    /**
     * カテゴリ種別によってアイコン画像を切り替え
     * @param categoryRole
     * @return Int
     */
    private fun getUserIcon(categoryRole: Int?): Int {
        return when(categoryRole) {
            0 -> R.drawable.ic_user_info_camera
            1 -> R.drawable.ic_user_info_model
            else -> R.drawable.ic_user_info_camera_model
        }
    }

    /**
     * カテゴリ種別によってアイコン画像の背景色を切り替え
     * @param categoryRole
     * @return Int
     */
    private fun getUserIconBg(categoryRole: Int?): Int {
        return when(categoryRole) {
            0 -> Color.argb(255,255,177,208)
            1 -> Color.argb(255,189,211,255)
            else -> Color.argb(255,150,240,180)
        }
    }

    /**
     * 有償・無償
     * @param charge
     * @return String
     */
    private fun getCharge(charge: Int?): String {
        return when(charge) {
            0 -> getString(R.string.charge_not)
            1 -> getString(R.string.charge_both)
            else -> getString(R.string.charge)
        }
    }

    /**
     * 年代
     * @param age
     * @return String
     */
    private fun getAge(age: Int?): String {
        return when(age) {
            0 -> getString(R.string.age_unselected)
            1 -> getString(R.string.age_10)
            2 -> getString(R.string.age_20)
            3 -> getString(R.string.age_30)
            4 -> getString(R.string.age_40)
            5 -> getString(R.string.age_50)
            else -> getString(R.string.age_60_adove)
        }
    }

    /**
     * 性別
     * @param sex
     * @return String
     */
    private fun getSex(sex: Int?): String {
        return when(sex) {
            0 -> getString(R.string.sex_not_select)
            1 -> getString(R.string.sex_man)
            else -> getString(R.string.sex_woman)
        }
    }

    /**
     * 地域
     * @param region
     * @return String
     */
    private fun getRegion(region: Int?): String {
        return when(region) {
            0 -> getString(R.string.region_hokkaido)
            1 -> getString(R.string.region_tohoku)
            2 -> getString(R.string.region_kanto)
            3 -> getString(R.string.region_hokuriku)
            4 -> getString(R.string.region_chubu)
            5 -> getString(R.string.region_kinki)
            6 -> getString(R.string.region_shikoku)
            7 -> getString(R.string.region_chugoku)
            8 -> getString(R.string.region_kyusyu)
            9 -> getString(R.string.region_okinawa)
            else -> getString(R.string.region_not_select)
        }
    }
}
