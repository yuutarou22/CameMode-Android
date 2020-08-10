package com.example.camemode.Fragment.UserInfoRegist


import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.camemode.Interface.TextWatchable

import com.example.camemode.R
import kotlinx.android.synthetic.main.fragment_user_info_regist_04.*

class UserInfoRegistFragment04 : Fragment() {

    companion object {
        const val MAX_PHOTO_IMAGE_LENGTH = 200
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_info_regist_04, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        photo_image_input.addTextChangedListener(object : TextWatchable {
            override fun onTextChanged(str: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var textColor: Int = Color.GRAY
                var textLength: Int = str!!.length
                photo_image_text_count.text = "${textLength}/200"
                if (textLength >= MAX_PHOTO_IMAGE_LENGTH)
                    textColor = Color.RED
                photo_image_text_count.setTextColor(textColor)
            }
        })
    }
}
