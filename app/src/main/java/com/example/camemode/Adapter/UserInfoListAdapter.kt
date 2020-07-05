package com.example.camemode.Adapter

import android.content.Context
import android.transition.Slide
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.camemode.Fragment.UserInfoDetailFragment
import com.example.camemode.Model.UserInfoModel
import com.example.camemode.R
import com.example.camemode.ViewHolder.UserInfoViewHolder

public class UserInfoListAdapter(list: ArrayList<UserInfoModel>, fragmentManager: FragmentManager, context: Context) :
    RecyclerView.Adapter<UserInfoViewHolder>() {

    private var list: ArrayList<UserInfoModel> = list
    private var fragmentManager: FragmentManager = fragmentManager
    private var context: Context = context
//    private lateinit var listener: View.OnClickListener

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
        viewHolder.mUserName.text = userInfoItem.displayName
        viewHolder.mSnsTranslationButton.setImageResource(R.drawable.ic_menu_arrow_down_black_24dp)
        viewHolder.mImaginationHope.text = userInfoItem.photoImage
    }

    /**
     * カテゴリ種別によってアイコン画像を切り替え
     * @param categoryRole
     * @return Int
     */
    private fun getUserIcon(categoryRole: Int): Int {
        return when(categoryRole) {
            0 -> R.drawable.ic_menu_arrow_down_black_24dp
            1 -> R.drawable.ic_calendar_black_24dp
            else -> R.drawable.design_password_eye
        }
    }

    /**
     * カテゴリ種別を文字列に変換
     * @param categoryRole
     * @return String
     */
    private fun getCategoryRole(categoryRole: Int): String {
        return when(categoryRole) {
            0 -> "カメラマン"
            1 -> "モデル"
            else -> "どちらとも"
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

        userInfoDetailFragment.enterTransition = slide
        fragmentTransaction.replace(R.id.fragment_home_container, userInfoDetailFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}