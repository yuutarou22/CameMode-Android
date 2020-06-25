package com.example.camemode.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.camemode.Model.UserInfo
import com.example.camemode.R
import com.example.camemode.ViewHolder.UserInfoViewHolder

public class UserInfoListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var list : List<UserInfo>
    private lateinit var fragmentManager: FragmentManager
    private lateinit var context: Context

    /**
     * コンストラクタ
     *
     * @param list ユーザ情報リスト
     * @param fragmentManager
     * @param context
     */
    public fun UserInfoListAdapter(list: List<UserInfo>, fragmentManager: FragmentManager, context: Context) {
        this.list = list
        this.fragmentManager = fragmentManager
        this.context = context
    }

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
        val inflate : View = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item_user_info, viewGroup, false)
        val viewHolder : UserInfoViewHolder = UserInfoViewHolder(inflate)
        return viewHolder
    }

    /**
     * 指定された位置のコンテンツを更新する
     * @param holder ViewHolder
     * @param position 位置
     */
    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

    }
}