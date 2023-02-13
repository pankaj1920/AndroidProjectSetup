package com.applaunch.appbase.adapter_base.base_binding_adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.applaunch.appbase.R
import com.applaunch.appbase.utils_base.BaseConstants
import com.applaunch.appbase.utils_base.color
import com.applaunch.appbase.utils_base.debounce
import com.applaunch.appbase.utils_base.hideKeyboard
import java.util.regex.Matcher
import java.util.regex.Pattern

@BindingAdapter(value = ["android:onClick", "debounceTime"], requireAll = false)
fun setSafeClick(view: View, onClickListener: View.OnClickListener, debounceTime: Int) {
    val time = if (debounceTime == 0) 300 else debounceTime
    val scope = ViewTreeLifecycleOwner.get(view)!!.lifecycleScope
    val clickDebounce: (view: View) -> Unit =
        debounce(scope = scope, delayMillis = time) {
            onClickListener.onClick(it)
        }
    view.setOnClickListener(clickDebounce)
}

@BindingAdapter("removeSpace")
fun removeSpace(view: EditText, removeSpace: Boolean) {
    view.doOnTextChanged { text, _, _, _ ->
        if (view.text.toString().contains(" ") && removeSpace) {
            view.setText(text.toString().replace(" ", ""))
            view.setSelection(view.text.toString().length)
        }
    }
}


@BindingAdapter("bgColor")
fun changeBgColor(view: View, bgColor: String) {
    try {
        val colorPattern = Pattern.compile("#([0-9a-f]{3}|[0-9a-f]{6}|[0-9a-f]{8})")
        val m: Matcher = colorPattern.matcher(bgColor)
        val isColor: Boolean = m.matches()
        if (isColor) view.setBackgroundColor(Color.parseColor(bgColor))
        else view.setBackgroundColor(view.context.resources.color(R.color.colorPrimary))
    } catch (e: Exception) {
        e.printStackTrace()

    }
}

@BindingAdapter("tintColor")
fun changeTintColor(view: ImageView, tintColor: String) {
    try {
        val colorPattern = Pattern.compile(BaseConstants.ColorPattern.ColorCodePattern)
        val m: Matcher = colorPattern.matcher(tintColor)
        val isColor: Boolean = m.matches()
        if (isColor) view.imageTintList = ColorStateList.valueOf((Color.parseColor(tintColor)))
        else view.imageTintList =
            ColorStateList.valueOf(ContextCompat.getColor(view.context, R.color.colorPrimary))
    } catch (e: Exception) {
        e.printStackTrace()

    }
    @BindingAdapter("dynamicTextviewIconColor")
    fun dynamicTextviewIconColor(view: TextView, tintColor: String) {
        try {
            val colorPattern = Pattern.compile(BaseConstants.ColorPattern.ColorCodePattern)
            val m: Matcher = colorPattern.matcher(tintColor)
            val isColor: Boolean = m.matches()
            TextViewCompat.setCompoundDrawableTintList(
                view,
                ColorStateList.valueOf((Color.parseColor(tintColor)))
            )

        } catch (e: Exception) {
            e.printStackTrace()

        }
    }

    @BindingAdapter("hideKeyboard")
    fun hideKeyboard(view: View, removeSpace: Boolean) {
        view.setOnClickListener {
            if (removeSpace) it.hideKeyboard()
        }
    }


}