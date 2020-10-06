package com.example.camemode.Adapter

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.camemode.Fragment.HomeFragment
import com.example.camemode.Fragment.UserInfoDetailFragment
import com.example.camemode.Model.UserInfo.Companion.FIELD_AGE
import com.example.camemode.Model.UserInfo.Companion.FIELD_CATEGORY_ROLE
import com.example.camemode.Model.UserInfo.Companion.FIELD_CHARGE
import com.example.camemode.Model.UserInfo.Companion.FIELD_DISPLAY_NAME
import com.example.camemode.Model.UserInfo.Companion.FIELD_PHOTO_IMAGE
import com.example.camemode.Model.UserInfo.Companion.FIELD_REGION
import com.example.camemode.Model.UserInfo.Companion.FIELD_SEX
import com.example.camemode.Model.UserInfo.Companion.FIELD_TWITTER_ID
import com.example.camemode.Model.UserInfoModel
import com.example.camemode.R
import com.example.camemode.ViewHolder.UserInfoViewHolder

class UserInfoListAdapter(list: ArrayList<UserInfoModel>, fragmentManager: FragmentManager, context: Context) :
    RecyclerView.Adapter<UserInfoViewHolder>() {

    private var list: ArrayList<UserInfoModel> = list
    private var fragmentManager: FragmentManager = fragmentManager
    private var context: Context = context

    /**
     * リスト要素の総数を返す
     * @return Int 総数
     */
    override fun getItemCount(): Int {
        return list.size
    }

    /**
     * ViewHolderの作成
     * @param viewGroup RecyclerViewで使用するフィールドを初期化する
     * @param viewType 新しいViewのViewType
     * @return ViewHoler
     */
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): UserInfoViewHolder {
        val inflate: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item_user_info, viewGroup, false)
        val viewHolder: UserInfoViewHolder = UserInfoViewHolder(inflate)
        return viewHolder
    }

    /**
     * 指定された位置のコンテンツを更新する
     * @param holder ViewHolder
     * @param position 位置
     */
    override fun onBindViewHolder(viewHolder: UserInfoViewHolder, position: Int) {
        val userInfoItem = list.get(position)

        viewHolder.itemView.setOnClickListener {
            android.util.Log.d("TEST", "itemView onClicked")
            createUserInfoDetailFragment(position)
        }

        viewHolder.mUserIcon.setImageResource(getUserIcon(userInfoItem.categoryRole))
        viewHolder.mCategoryRole.text = getCategoryRole(userInfoItem.categoryRole)
        viewHolder.mUserIcon.setBackgroundColor(getUserIconBg(userInfoItem.categoryRole))
        viewHolder.mUserName.text = userInfoItem.displayName
        viewHolder.mSnsTranslationButton.setImageResource(R.drawable.ic_twitter_button)
        viewHolder.mImaginationHope.text = userInfoItem.photoImage
    }

    /**
     * カテゴリ種別によってアイコン画像を切り替え
     * @param categoryRole
     * @return Int
     */
    private fun getUserIcon(categoryRole: Int): Int {
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
    private fun getUserIconBg(categoryRole: Int): Int {
        return when(categoryRole) {
            0 -> Color.argb(255,255,177,208)
            1 -> Color.argb(255,189,211,255)
            else -> Color.argb(255,150,240,180)
        }
    }

    /**
     * カテゴリ種別を文字列に変換
     * @param categoryRole
     * @return String
     */
    private fun getCategoryRole(categoryRole: Int): String {
        return when(categoryRole) {
            0 -> context.resources.getString(R.string.category_role_camera)
            1 -> context.resources.getString(R.string.category_role_model)
            else -> context.resources.getString(R.string.category_role_camera_model)
        }
    }

    /**
     * 選択したユーザ情報のFragmentを生成する
     * @param position
     */
    private fun createUserInfoDetailFragment(position: Int) {
        var slide = Slide(Gravity.BOTTOM)

        var fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        var userInfoDetailFragment: UserInfoDetailFragment = UserInfoDetailFragment()

        userInfoDetailFragment.arguments = createUserInfoToBundle(list.get(position))

        userInfoDetailFragment.enterTransition = slide

        if (fragmentManager.fragments.get(0) is HomeFragment) {
            fragmentTransaction.replace(R.id.fragment_home_container, userInfoDetailFragment)
        } else {
            fragmentTransaction.replace(R.id.fragment_search_result_container, userInfoDetailFragment)
        }

        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun createUserInfoToBundle(userInfoModel: UserInfoModel):Bundle {
        var bundle = Bundle()

        bundle.putInt(FIELD_CATEGORY_ROLE, userInfoModel.categoryRole)
        bundle.putString(FIELD_DISPLAY_NAME, userInfoModel.displayName)
        bundle.putString(FIELD_PHOTO_IMAGE, userInfoModel.photoImage)
        bundle.putString(FIELD_TWITTER_ID, userInfoModel.twitterId)
        bundle.putInt(FIELD_AGE, userInfoModel.age)
        bundle.putInt(FIELD_REGION, userInfoModel.region)
        bundle.putInt(FIELD_SEX, userInfoModel.sex)
        bundle.putInt(FIELD_CHARGE, userInfoModel.charge)

        return bundle
    }
}