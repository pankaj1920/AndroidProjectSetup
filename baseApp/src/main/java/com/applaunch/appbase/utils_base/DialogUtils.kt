package com.applaunch.appbase.utils_base

import android.app.Activity
import androidx.fragment.app.Fragment
import com.applaunch.appbase.R
import com.applaunch.appbase.databinding.ErrorLayoutBinding
import shareOnAllApps

fun Activity.toastMessage(message: Any?) {
    if (message is String) {
        Toaster.show(this, message)
    } else if (message is Int) {
        Toaster.show(this, message)
    }
}

fun Activity.dismissProgressBar(progressBar: CustomProgressBar?) {
    progressBar?.dialog?.let {
        if (it.isShowing)
            it.dismiss()
    }
}

fun Activity.showProgressBar(progressBar: CustomProgressBar) {
    dismissProgressBar(progressBar)
    //Progress Bar with Text
    progressBar.show(this, null)
}

fun Activity.showProgressBar(progressBar: CustomProgressBar?, message: String?) {
    dismissProgressBar(progressBar)
    //Progress Bar with Text
    progressBar?.show(this, message)
}

fun Fragment.toastMessage(message: Any?) {
    if (message is String) {
        Toaster.show(context!!, message)
    } else if (message is Int) {
        Toaster.show(context!!, message)
    }
}

fun Fragment.dismissProgressBar(progressBar: CustomProgressBar?) {
    progressBar?.dialog?.let {
        if (it.isShowing)
            it.dismiss()
    }
}

fun Fragment.showProgressBar(progressBar: CustomProgressBar) {
    dismissProgressBar(progressBar)
    //Progress Bar with Text
    progressBar.show(context!!, null)
}

fun Fragment.showProgressBar(progressBar: CustomProgressBar?, message: String?) {
    dismissProgressBar(progressBar)
    //Progress Bar with Text
    progressBar?.show(context!!, message)
}


fun Fragment.showBottomDialog(message: String, anim: Int = R.raw.network_error) {

    val binding = ErrorLayoutBinding.inflate(layoutInflater)
    val bottomSheet = CustomDialog(requireContext()).createBottomSheet(binding)

    binding.ivClose.setOnClickListener {

        bottomSheet.second.dismiss()
        requireActivity().finish()
    }

    binding.ivMinimise.setOnClickListener { bottomSheet.second.dismiss() }

    binding.ivShare.setOnClickListener {
        shareOnAllApps(requireContext(), message)
    }
    Print.log(message)
    binding.tvMessage.text = message
    binding.lottieAnim.setAnimation(anim)
    binding.lottieAnim

}