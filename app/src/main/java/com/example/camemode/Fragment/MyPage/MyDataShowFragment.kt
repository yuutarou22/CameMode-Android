package com.example.camemode.Fragment.MyPage

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.*
import com.example.camemode.Fragment.BaseFragment
import com.example.camemode.Model.UserInfo

import com.example.camemode.R
import kotlinx.android.synthetic.main.content_user_info_age.*
import kotlinx.android.synthetic.main.content_user_info_charge.*
import kotlinx.android.synthetic.main.content_user_info_photo_image.*
import kotlinx.android.synthetic.main.content_user_info_region.*
import kotlinx.android.synthetic.main.content_user_info_sex.*
import kotlinx.android.synthetic.main.fragment_user_info_detail.*

class MyDataShowFragment : BaseFragment() {

    /**
     * ローカル保持用
     */
    lateinit var data : SharedPreferences
    lateinit var editor : SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        data = requireContext().getSharedPreferences("UserInfoData", Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_info_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {

        region.text = getRegion(data.getInt(UserInfo.FIELD_REGION, 1))
        category_role_icon.setImageResource(getUserIcon(data.getInt(UserInfo.FIELD_CATEGORY_ROLE, 1)))
        category_role_icon.setBackgroundColor(getUserIconBg(data.getInt(UserInfo.FIELD_CATEGORY_ROLE, 1)))
        category_role_text.text = getCategoryRole(data.getInt(UserInfo.FIELD_CATEGORY_ROLE, 1))
        charge.text = getCharge(data.getInt(UserInfo.FIELD_CHARGE, 1))
        age.text = getAge(data.getInt(UserInfo.FIELD_AGE, 1))
        display_name.text = data.getString(UserInfo.FIELD_DISPLAY_NAME, "")
        photo_image.text = data.getString(UserInfo.FIELD_PHOTO_IMAGE, "")
        sex.text = getSex(data.getInt(UserInfo.FIELD_SEX, 1))

        edit_bubtton.visibility = View.VISIBLE

        edit_bubtton.setOnClickListener {
            showFragment(MyDataEditFragment())
        }

        back_button.setOnClickListener {
            fragmentManager?.popBackStack()
        }

        setupSnsButton(data.getString(UserInfo.FIELD_TWITTER_ID, ""), data.getString(UserInfo.FIELD_INSTAGRAM_ID, ""))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
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
