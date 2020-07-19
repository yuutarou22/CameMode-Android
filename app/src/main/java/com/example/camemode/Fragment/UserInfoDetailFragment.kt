package com.example.camemode.Fragment

import android.content.Context
import android.graphics.Color
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
        region.text = arguments?.getInt(FIELD_REGION).toString()
        category_role_icon.setImageResource(getUserIcon(arguments?.getInt(FIELD_CATEGORY_ROLE)))
        category_role_icon.setBackgroundColor(getUserIconBg(arguments?.getInt(FIELD_CATEGORY_ROLE)))
        category_role_text.text = arguments?.getInt(FIELD_CATEGORY_ROLE).toString()
        charge.text = arguments?.getInt(FIELD_CHARGE).toString()
        display_name.text = arguments?.getString(FIELD_DISPLAY_NAME)
        photo_image.text = arguments?.getString(FIELD_PHOTO_IMAGE)
        sex.text = arguments?.getInt(FIELD_SEX).toString()
        twitter_id.text = arguments?.getString(FIELD_TWITTER_ID)

        back_button.setOnClickListener {
            fragmentManager?.popBackStack()
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
}
