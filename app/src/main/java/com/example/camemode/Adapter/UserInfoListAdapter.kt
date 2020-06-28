package com.example.camemode.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.camemode.Model.UserInfoModel
import com.example.camemode.R
import com.example.camemode.ViewHolder.UserInfoViewHolder

public class UserInfoListAdapter(list: ArrayList<UserInfoModel>, fragmentManager: FragmentManager, context: Context) :
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
        val inflate: View =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item_user_info, viewGroup, false)
        val viewHolder: UserInfoViewHolder = UserInfoViewHolder(inflate)
        return viewHolder
    }

    /**
     * 指定された位置のコンテンツを更新する
     * @param holder ViewHolder
     * @param position 位置
     */
    override fun onBindViewHolder(viewHolder: UserInfoViewHolder, position: Int) {
        viewHolder.mLineView.context.resources.getColor(R.color.cardview_shadow_end_color)
        viewHolder.mUserIcon.setImageResource(R.drawable.ic_menu_arrow_down_black_24dp)
        viewHolder.mUserName.text = list.get(position).displayName
        viewHolder.mSnsTranslationButton.setImageResource(R.drawable.ic_menu_arrow_down_black_24dp)
        viewHolder.mCategoryRole.text = list.get(position).categoryRole.toString()
        viewHolder.mImaginationHope.text = list.get(position).photoImage
        viewHolder.mAge.text = list.get(position).age.toString()
        viewHolder.mSex.text = list.get(position).sex.toString()
    }
}