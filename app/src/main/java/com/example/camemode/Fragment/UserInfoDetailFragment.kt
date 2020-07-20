package com.example.camemode.Fragment

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.camemode.Model.UserInfo.Companion.FIELD_CATEGORY_ROLE
import com.example.camemode.Model.UserInfo.Companion.FIELD_CHARGE
import com.example.camemode.Model.UserInfo.Companion.FIELD_DISPLAY_NAME
import com.example.camemode.Model.UserInfo.Companion.FIELD_PHOTO_IMAGE
import com.example.camemode.Model.UserInfo.Companion.FIELD_REGION
import com.example.camemode.Model.UserInfo.Companion.FIELD_SEX
import com.example.camemode.Model.UserInfo.Companion.FIELD_TWITTER_ID

import com.example.camemode.R
import kotlinx.android.synthetic.main.content_user_info_charge.*
import kotlinx.android.synthetic.main.content_user_info_photo_image.*
import kotlinx.android.synthetic.main.content_user_info_region.*
import kotlinx.android.synthetic.main.content_user_info_sex.*
import kotlinx.android.synthetic.main.fragment_user_info_detail.*

class UserInfoDetailFragment : Fragment() {
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
        region.text = getRegion(arguments?.getInt(FIELD_REGION))
        category_role_icon.setImageResource(getUserIcon(arguments?.getInt(FIELD_CATEGORY_ROLE)))
        category_role_icon.setBackgroundColor(getUserIconBg(arguments?.getInt(FIELD_CATEGORY_ROLE)))
        category_role_text.text = getCategoryRole(arguments?.getInt(FIELD_CATEGORY_ROLE))
        charge.text = getCharge(arguments?.getInt(FIELD_CHARGE))
        display_name.text = arguments?.getString(FIELD_DISPLAY_NAME)
        photo_image.text = arguments?.getString(FIELD_PHOTO_IMAGE)
        sex.text = getSex(arguments?.getInt(FIELD_SEX))

        back_button.setOnClickListener {
            fragmentManager?.popBackStack()
        }

        sns_transition_button.setOnClickListener {
            val uri:Uri = Uri.parse(context?.resources?.getString(R.string.twitter_url) + arguments?.getString(FIELD_TWITTER_ID) + "/")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }

    /**
     * カテゴリ種別を文字列に変換
     * @param categoryRole
     * @return String
     */
    private fun getCategoryRole(categoryRole: Int?): String {
        return when(categoryRole) {
            0 -> "カメラマン"
            1 -> "モデル"
            else -> "カメラマン/モデル"
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
            0 -> "無償"
            else -> "有償"
        }
    }

    /**
     * 性別
     * @param sex
     * @return String
     */
    private fun getSex(sex: Int?): String {
        return when(sex) {
            0 -> "未選択"
            1 -> "男性"
            else -> "女性"
        }
    }

    /**
     * 地域
     * @param region
     * @return String
     */
    private fun getRegion(region: Int?): String {
        return when(region) {
            0 -> "北海道"
            1 -> "東北"
            2 -> "関東"
            3 -> "北陸"
            4 -> "中部"
            5 -> "近畿"
            6 -> "四国"
            7 -> "中国"
            8 -> "九州"
            9 -> "沖縄"
            else -> "未選択"
        }
    }
}
