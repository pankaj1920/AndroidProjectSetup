package com.applaunch.appbase.utils_base

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.databinding.ViewDataBinding
import com.applaunch.appbase.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class CustomDialog(val context: Context) {

    fun createAlert(binding: ViewDataBinding,cancelable:Boolean = true): Pair<View, AlertDialog> {
        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder.setView(binding.root)
        val alertDialog = dialogBuilder.create()
        alertDialog.setCancelable(cancelable)
        alertDialog.show()
        return Pair(binding.root, alertDialog)
    }

    fun createBottomSheet(binding: ViewDataBinding,cancelable:Boolean = true): Pair<View, BottomSheetDialog>{
        val bottomSheetDialog = BottomSheetDialog(context)
        bottomSheetDialog.setContentView(binding.root)
        bottomSheetDialog.window?.setBackgroundDrawable(ColorDrawable(context.resources.color(R.color.dark_blur)))
        bottomSheetDialog.setCancelable(cancelable)
        bottomSheetDialog.show()
        return Pair(binding.root, bottomSheetDialog)
    }


    fun createCustomDialog(binding: ViewDataBinding,cancelable:Boolean = true): Pair<View, Dialog>{
        val dialog = Dialog(context)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(binding.root)
            window!!.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
            )
            window?.setBackgroundDrawable(ColorDrawable(context.resources.color(R.color.dark_blur)))
            dialog.setCancelable(cancelable)
            show()
            return Pair(binding.root, dialog)
        }
    }


}