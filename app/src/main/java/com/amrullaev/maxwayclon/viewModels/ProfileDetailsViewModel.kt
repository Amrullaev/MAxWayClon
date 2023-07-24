package com.amrullaev.maxwayclon.viewModels

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface ProfileDetailsViewModel {

    val nameFlow: StateFlow<String>

    val phoneFlow: StateFlow<String>

    val birthdayFlow:StateFlow<String>

    val openCalendarDialog: SharedFlow<Unit>

    fun openCalendar()

    fun saveClicked(name: String, birthday: String)


}