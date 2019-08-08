package com.sphinx.dailystorykt

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar
import org.jetbrains.anko.inputMethodManager
import java.io.InputStream

/*
 * General Utilities for the app
 */

fun Activity.showSoftKeyBoard(view: View?) {
    showSoftKeyboardWithDelay(view)
}

fun Activity.showSoftKeyboardWithDelay(view: View?) {
    Handler().postDelayed({
        view?.requestFocus()
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }, 100L)
}

fun View.snackbar(text: CharSequence, duration: Int = Snackbar.LENGTH_SHORT, init: Snackbar.() -> Unit = {}): Snackbar {
    val snack = Snackbar.make(this, text, duration)
    snack.init()
    snack.show()
    return snack
}

/**
 * Handy utility to store key-value pairs in SharedPreferences
 * without the hassle of 'apply'
 *
 * Usage (in Activity/Fragment):
 * ```
 * defaultSharedPreferences.apply("ACCESS_TOKEN" to "getAccessToken", "NAME" to "name")
 * ```
 *
 * @param pairs Pairs of key and value to store
 */
fun SharedPreferences.apply(vararg pairs: Pair<String, String?>) {
    edit().apply {
        pairs.forEach { putString(it.first, it.second) }
    }.apply()
}

fun SharedPreferences.remove(vararg keys: String) {
    edit().apply {
        keys.forEach { remove(it) }
    }.apply()
}

inline fun inDebug(crossinline f: () -> Unit) {
    if (BuildConfig.DEBUG) f()
}

fun String.splitByCommaSpace() = split(' ', ',').filterNot(String::isBlank)


fun Activity.hideSoftKeyboardWithDelay() {
    Handler().postDelayed({ hideSoftKeyboard() }, 100L)
}

fun Activity.hideSoftKeyboard() {
    currentFocus?.let {
        inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

fun Activity.showSoftKeyboard(view: View?) {
    Handler().postDelayed(
        {
            view?.requestFocus()
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }, 100L)
}

fun InputStream.joinToString(): String = bufferedReader()
    .readLines()
    .joinToString(separator = "")

fun Application.isNetworkConnected(): Boolean {
    val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo
    activeNetwork = cm.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnectedOrConnecting
}