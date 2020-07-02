package com.example.camemode.ViewHolder

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.camemode.R

class UserInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val mUserIcon: ImageView = itemView.findViewById(R.id.user_icon)
    val mUserName: TextView = itemView.findViewById(R.id.user_name)
    val mSnsTranslationButton: ImageButton = itemView.findViewById(R.id.sns_transition)
    val mImaginationHope: TextView = itemView.findViewById(R.id.imagination_hope)
}