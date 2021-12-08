package com.goldcompany.apps.koreabike.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.goldcompany.apps.koreabike.MainActivity

class ViewHelper {
    companion object {
        fun hideKeyboard(view: View) {
            val imm = MainActivity.instance.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}