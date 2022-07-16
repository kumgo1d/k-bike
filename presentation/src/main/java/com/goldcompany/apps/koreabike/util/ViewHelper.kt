package com.goldcompany.apps.koreabike.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.goldcompany.apps.koreabike.MainActivity
import com.goldcompany.apps.koreabike.R

class ViewHelper {
    companion object {
        fun hideKeyboard(view: View) {
            val imm = MainActivity.instance.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

        fun errorToast(context: Context) {
            Toast.makeText(context, R.string.error_code, Toast.LENGTH_SHORT).show()
        }
    }
}