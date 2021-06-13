package com.goldcompany.apps.koreabike

import android.app.Activity
import android.app.Application
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.goldcompany.apps.koreabike.db.KBikeDatabase

class KBikeApplication: Application() {
    companion object {
        lateinit var instance: KBikeApplication
            private set

        fun hideKeyboard(input: EditText, activity: Activity, view: View) {
            input.clearFocus()

            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    val database by lazy { KBikeDatabase.getInstance(this)}

    init {
        instance = this
    }
}