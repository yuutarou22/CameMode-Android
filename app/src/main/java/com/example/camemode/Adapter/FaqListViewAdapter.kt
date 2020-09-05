package com.example.camemode.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.camemode.Model.FaqInfo
import com.example.camemode.R
import com.example.camemode.ViewHolder.FaqListViewHolder

public class FaqListViewAdapter(list: ArrayList<FaqInfo>, fragmentManager: FragmentManager, context: Context) :
        RecyclerView.Adapter<FaqListViewHolder>() {

    private var list: ArrayList<FaqInfo> = list
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
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): FaqListViewHolder {
        val inflate: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item_faq, viewGroup, false)
        val viewHolder: FaqListViewHolder = FaqListViewHolder(inflate)
        return viewHolder
    }

    /**
     * 指定された位置のコンテンツを更新する
     * @param viewHolder ViewHolder
     * @param position 位置
     */
    override fun onBindViewHolder(viewHolder: FaqListViewHolder, position: Int) {
        val FaqInfoItem = list.get(position)

        viewHolder.mOpenCloseButton.setOnClickListener {
            viewHolder.mAnswerText.visibility = if(viewHolder.mAnswerText.visibility == View.GONE) View.VISIBLE else View.GONE

        }

        viewHolder.mQuestionText.text = FaqInfoItem.question
        viewHolder.mAnswerText.text = FaqInfoItem.answer
    }

}