package com.amrullaev.maxwayclon.ui.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amrullaev.maxwayclon.data.prefs.MySharedPref
import com.amrullaev.maxwayclon.databinding.DialogLanguageBinding
import com.amrullaev.maxwayclon.utils.extensions.inVisible
import com.amrullaev.maxwayclon.utils.extensions.visible
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChooseLanguageDialog : BottomSheetDialogFragment() {

    private lateinit var binding: DialogLanguageBinding

    private var changeLanguageListener:(()->Unit)? = null

    fun setChangeLanguageListener(block:()->Unit){
        changeLanguageListener = block
    }

    @Inject
    lateinit var mySharedPref: MySharedPref

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogLanguageBinding.inflate(layoutInflater)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        checkLanguage()
        binding.containerUzbekLng.setOnClickListener {
            mySharedPref.language = 0
            checkLanguage()
        }
        binding.containerEnglishLng.setOnClickListener {
            mySharedPref.language = 1
            checkLanguage()
        }

        return binding.root
    }

    private fun checkLanguage() {
        val lan = mySharedPref.language
        binding.apply {
            if (lan == 0) {
                imageUzbCheck.visible()
                imageEngCheck.inVisible()
            } else {
                imageUzbCheck.inVisible()
                imageEngCheck.visible()
            }
        }
        changeLanguageListener?.invoke()
    }
}