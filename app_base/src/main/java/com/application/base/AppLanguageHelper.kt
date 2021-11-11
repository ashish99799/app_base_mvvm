package com.application.base

import android.os.Build
import androidx.preference.PreferenceManager
import android.annotation.TargetApi
import android.content.Context
import android.net.ConnectivityManager
import java.util.*

private const val SELECTED_LANGUAGE = "SELECTED_LANGUAGE"
private const val ERROR_MESSAGE_KEY = "ERROR_MESSAGE_KEY"

fun Context.isNetworkConnectionAvailable(): Boolean {
    val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    return (activeNetwork != null && activeNetwork.isConnected)
}

// the method is used to set the language at runtime
fun Context.setLocale(language: String): Context {
    setLanguage(language)

    // updating the language for devices above android nougat
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        updateResources(language)
    } else updateResourcesLegacy(language)
    // for devices having lower version of android os
}

// the method is used update the language of application by creating
// object of inbuilt Locale class and passing language argument to it
@TargetApi(Build.VERSION_CODES.N)
private fun Context.updateResources(language: String): Context {
    val locale = Locale(language)
    Locale.setDefault(locale)
    val configuration = resources.configuration
    configuration.setLocale(locale)
    configuration.setLayoutDirection(locale)
    return createConfigurationContext(configuration)
}

@SuppressWarnings("deprecation")
private fun Context.updateResourcesLegacy(language: String): Context {
    val locale = Locale(language)
    Locale.setDefault(locale)
    val resources = resources
    val configuration = resources.configuration
    configuration.locale = locale
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        configuration.setLayoutDirection(locale)
    }
    resources.updateConfiguration(configuration, resources.displayMetrics)
    return this
}

fun Context.bindLanguage(language_code: String? = getLanguage()) {
    val config = resources.configuration
    val locale = Locale(language_code)
    Locale.setDefault(locale)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
        config.setLocale(locale)
    else
        config.locale = locale

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        createConfigurationContext(config)
    resources.updateConfiguration(config, resources.displayMetrics)
}

fun Context.setLanguage(language: String) {
    val editor = PreferenceManager.getDefaultSharedPreferences(this).edit()
    editor.putString(SELECTED_LANGUAGE, language)
    editor.apply()
}

fun Context.getLanguage(): String {
    return PreferenceManager.getDefaultSharedPreferences(this).getString(SELECTED_LANGUAGE, "en") ?: "en"
}

fun Context.setErrorMessage(language: String) {
    val editor = PreferenceManager.getDefaultSharedPreferences(this).edit()
    editor.putString(ERROR_MESSAGE_KEY, language)
    editor.apply()
}

fun Context.getErrorMessage(): String {
    return PreferenceManager.getDefaultSharedPreferences(this).getString(ERROR_MESSAGE_KEY, "msg") ?: "msg"
}
