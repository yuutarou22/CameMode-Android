package com.example.camemode.Fragment.Dialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.camemode.R

class RegistAlertDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)

            builder.setTitle(getString(R.string.regist_alert_dialog_confirm_title))
                .setMessage(R.string.regist_alert_dialog_confirm_message)
                .setNeutralButton("はい", { dialog, which ->
                    // ToDo: バリデーションチェック処理
                    
                    // ToDo: NCMBへの登録処理
                })
                .setPositiveButton("いいえ", { dialog, which ->

                })

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be nunll")
    }
}