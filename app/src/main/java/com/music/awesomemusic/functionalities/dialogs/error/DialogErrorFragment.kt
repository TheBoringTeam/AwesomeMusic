package com.music.awesomemusic.functionalities.dialogs.error

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.music.awesomemusic.R
import kotlinx.android.synthetic.main.dialog_error.view.*

class DialogErrorFragment(private val errorMessage: String) : DialogFragment() {
    private lateinit var _listener: ErrorDialogListener

    interface ErrorDialogListener {
        fun onSubmit(dialog: DialogFragment)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val dialog = super.onCreateDialog(savedInstanceState)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            return dialog
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_error, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.dialog_error_tv_error.text = errorMessage
        view.dialog_error_btn_submit.setOnClickListener {
            dialog?.cancel()
        }
    }
}