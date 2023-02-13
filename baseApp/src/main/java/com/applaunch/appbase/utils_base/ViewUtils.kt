package com.applaunch.appbase.utils_base

import android.app.Service
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import doNothing


fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            doNothing()
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            doNothing()
        }
    })
}

fun String.isValidEmail(): Boolean =
    !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()


fun View.getValue(): String {
    return when (this) {
        is EditText -> this.text.toString().trim()
        is TextView -> this.text.toString().trim()
        else -> ""
    }
}

fun View.setValue(string: String) {
    when (this) {
        is EditText -> this.setText(string)
        is TextView -> this.text = string
        else -> Print.log("Invalid View to Set Data")
    }
}

fun EditText.isNN(): Boolean {
    return this.getValue().isNotEmpty()
}


fun EditText.validate(msg: String, setError: Boolean = false): Boolean {
    if (this.text.toString().isEmpty()) {
        if (setError) {
            this.requestFocus()
            this.error = msg
        }
        return false
    }
    return true
}

fun String.equalIgnore(str: String): Boolean {
    return this.equals(str, true)
}


fun View.showKeyboard() {
    (this.context.getSystemService(Service.INPUT_METHOD_SERVICE) as? InputMethodManager)
        ?.showSoftInput(this, 0)
}

fun View.hideKeyboard() {
    (this.context.getSystemService(Service.INPUT_METHOD_SERVICE) as? InputMethodManager)
        ?.hideSoftInputFromWindow(this.windowToken, 0)
}



fun TextView.setDrawable(drawableIcon: Int, drawablePosition: String) {
    if (drawablePosition.equalIgnore(BaseConstants.Position.START))
        this.setCompoundDrawablesWithIntrinsicBounds(drawableIcon, 0, 0, 0);
    if (drawablePosition.equalIgnore(BaseConstants.Position.END))
        this.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawableIcon, 0);
    if (drawablePosition.equalIgnore(BaseConstants.Position.TOP))
        this.setCompoundDrawablesWithIntrinsicBounds(0, drawableIcon, 0, 0);
    if (drawablePosition.equalIgnore(BaseConstants.Position.BOTTOM))
        this.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, drawableIcon);


}

fun EditText.removeSpace() {
    this.apply {
        doOnTextChanged { text, _, _, _ ->
            if (text.toString().contains(" ")) {
                setText(text.toString().replace(" ", ""))
                setSelection(getText().toString().length);
            }
        }
    }
}

    fun View.visible() {
        visibility = View.VISIBLE
    }

    fun View.invisible() {
        visibility = View.INVISIBLE
    }

    fun View.gone() {
        visibility = View.GONE
    }

fun View.isVisible(): Boolean = visibility == View.VISIBLE

fun View.visibleIf(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

