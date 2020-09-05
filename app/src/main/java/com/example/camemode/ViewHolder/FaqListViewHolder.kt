package com.example.camemode.ViewHolder

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.camemode.R

class FaqListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val mQuestionText: TextView = itemView.findViewById(R.id.question_text)
    val mOpenCloseButton: ImageButton = itemView.findViewById(R.id.open_close_button)
    val mAnswerText: TextView = itemView.findViewById(R.id.answer_text)
}