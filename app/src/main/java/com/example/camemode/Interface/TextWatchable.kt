package com.example.camemode.Interface

import android.text.Editable
import android.text.TextWatcher

/**
 * TextWatcherで冗長な処理をスッキリさせるためのインターフェース
 */
interface TextWatchable : TextWatcher{
    override fun afterTextChanged(p0: Editable?) {}
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
}