package com.example.camemode.Fragment.Dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.camemode.R

class RegistAlertDialogFragment : DialogFragment(){

    private var mListener: DialogOkClickListener? = null

    interface DialogOkClickListener {
        fun onOkClicked(dialog: DialogFragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            mListener = this.targetFragment as DialogOkClickListener
        } catch (e: ClassCastException) {
            throw ClassCastException((context.toString() +
                    " must implement DialogOkClickListener"))
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)

            builder.setTitle(getString(R.string.regist_alert_dialog_confirm_title))
                .setMessage(R.string.regist_alert_dialog_confirm_message)
                .setNeutralButton("はい", { dialog, which ->
                    // ToDo: NCMBへの登録処理
                    mListener?.onOkClicked(this)
                })
                .setPositiveButton("いいえ", { dialog, which -> })

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be nunll")
    }
}