package com.example.mymovieapp.utils

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import com.example.mymovieapp.R

class LoaderDialog(context: Context) : AlertDialog(context) {

    override fun show() {
        super.show()
        setContentView(R.layout.loader_dialog_progressbar)
        window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onBackPressed() {
        cancel()
        super.onBackPressed()
    }

    fun showLoader() {
        if (!this.isShowing)
            show()
    }

    fun hideLoader() {
        if (this.isShowing)
            dismiss()
    }
}