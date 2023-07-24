package com.amrullaev.maxwayclon.data.prefs

import android.content.Context
import android.content.SharedPreferences
import com.amrullaev.maxwayclon.utils.SharedPreference
import com.amrullaev.maxwayclon.utils.extensions.getCurrentDate
import javax.inject.Inject

class MySharedPref @Inject constructor(ctx: Context, sharedPreferences: SharedPreferences) :
    SharedPreference(ctx, sharedPreferences) {

    var token: String by Strings("")

    var phone: String by Strings()

    var name: String by Strings()

    var birthday: String by Strings(getCurrentDate())

    var language: Int by Ints(1)

}