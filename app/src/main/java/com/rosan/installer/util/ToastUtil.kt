package com.rosan.installer.util

import android.widget.Toast
import androidx.annotation.StringRes
import com.rosan.installer.App


fun toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(App.context, text, duration).show()
}

fun toast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(App.context, resId, duration).show()
}